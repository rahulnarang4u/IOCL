from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy 
from flask_marshmallow import Marshmallow
import random 
import os

# Init app
app = Flask(__name__)
basedir = os.path.abspath(os.path.dirname(__file__))
# Database
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + os.path.join(basedir, 'db.sqlite')
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
# Init db
db = SQLAlchemy(app)
# Init ma
ma = Marshmallow(app)

# Complaint add
class Complaint(db.Model):
  complaint_no = db.Column(db.Integer, primary_key=True)
  sapcode = db.Column(db.Integer, nullable=False)
  auto_vendor = db.Column(db.String(100), nullable=False)
  d_office = db.Column(db.String(50), nullable=False)
  issue = db.Column(db.String(100))
  remarks = db.Column(db.String(200))
  priority = db.Column(db.String(20))
  name = db.Column(db.String(100), nullable=False)
  type = db.Column(db.String(100))
  vsat_ip = db.Column(db.String(100))
  vsat_vendor = db.Column(db.String(100))
  date_of_issue = db.Column(db.String(100))

  def __init__(self, sapcode,complaint_no, auto_vendor, d_office, issue, remarks, priority, name, type, vsat_ip, vsat_vendor, date_of_issue):
    
    self.sapcode = sapcode
    self.complaint_no = complaint_no
    self.auto_vendor = auto_vendor
    self.d_office = d_office
    self.issue = issue
    self.remarks = remarks
    self.priority = priority
    self.name = name
    self.type = type
    self.vsat_ip = vsat_ip
    self.vsat_vendor = vsat_vendor
    self.date_of_issue = date_of_issue

# Product Schema
class ComplaintSchema(ma.Schema):
  class Meta:
    fields = ( 'sapcode','complaint_no', 'auto_vendor', 'd_office', 'issue', 'remarks', 'priority', 'name', 'type', 'vsat_ip', 'vsat_vendor', 'date_of_issue')

# Init schema
complaint_schema = ComplaintSchema(strict=True)
complaints_schema = ComplaintSchema(many=True, strict=True)

# Create a Product
@app.route('/complaint', methods=['POST'])
def add_complaint():

  complaint_no = request.json['complaint_no']
  sapcode = request.json['sapcode']
  auto_vendor = request.json['auto_vendor']
  d_office = request.json['d_office']
  issue = request.json['issue']
  remarks = request.json['remarks']
  priority = request.json['priority']
  name = request.json['name']
  type = request.json['type']
  vsat_ip = request.json['vsat_ip']
  vsat_vendor = request.json['vsat_vendor']
  date_of_issue = request.json['date_of_issue']
  
  new_complaint = Complaint( sapcode,complaint_no, auto_vendor, d_office, issue, remarks, priority, name, type, vsat_ip, vsat_vendor, date_of_issue)

  db.session.add(new_complaint)
  db.session.commit()
  return complaint_schema.jsonify(new_complaint)


# Get All Products
@app.route('/complaint', methods=['GET'])
def get_complaints():
  all_complaints = Complaint.query.all()
  result = complaints_schema.dump(all_complaints)
  return jsonify(result.data)

# Get Product
@app.route('/complaint/<id>', methods=['GET'])
def get_complaint(id):
  complaint = Complaint.query.get(id)
  return complaint_schema.jsonify(complaint)

# Run Server
if __name__ == '__main__':
  app.run(host="0.0.0.0", port="5000")
  app.run(debug=True)
