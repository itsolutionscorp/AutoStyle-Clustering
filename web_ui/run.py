#!/usr/bin/env python
from app import app
port = 5000
for i in range(10):
	try:
		app.run(debug=True, port=port+i)
		break
	except:
		pass
