from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt

app = Flask(__name__)
# Configuración básica de SQLite
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///users.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)
bcrypt = Bcrypt(app)

# Modelo de Base de Datos
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(50), unique=True, nullable=False)
    password = db.Column(db.String(80), nullable=False)

# Crear la base de datos si no existe
with app.app_context():
    db.create_all()

# Endpoint 1: Verificar que la API está viva
@app.route('/', methods=['GET'])
def index():
    return jsonify({"mensaje": "¡La API está activa, carnal!"}), 200

# Endpoint 2: Registrar usuario
@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    hashed_password = bcrypt.generate_password_hash(data['password']).decode('utf-8')
    
    nuevo_usuario = User(username=data['username'], password=hashed_password)
    
    try:
        db.session.add(nuevo_usuario)
        db.session.commit()
        return jsonify({"mensaje": "Usuario registrado con éxito"}), 201
    except:
        db.session.rollback()
        return jsonify({"error": "El usuario ya existe"}), 400

# Endpoint 3: Login
@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    user = User.query.filter_by(username=data['username']).first()
    
    if user and bcrypt.check_password_hash(user.password, data['password']):
        return jsonify({"mensaje": f"Bienvenido {user.username}"}), 200
    else:
        return jsonify({"error": "Credenciales incorrectas"}), 401

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)