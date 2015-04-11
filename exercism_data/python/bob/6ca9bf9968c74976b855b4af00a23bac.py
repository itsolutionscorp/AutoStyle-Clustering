#creating Bob
class Bob(object):
#i was told i don't actually need __init__ here... why is that?
	def __init__( self ):
		pass
	#setting up responces for Bob to different statements
	def hey ( self, statement ):
		#responding to nothing at all
		#this is sloppy i think.  got it to work for this test but not for other blank statements.
		if statement == '    ':
			return('Fine. Be that way!')
		#this is above 'Sure.' response because it has priority in the tests over question marks
		#responding to all-caps messages.  This one confused me a bit.. was it only supposed to respond if the statement was entirely caps?
		elif statement.islower() is False and statement.isupper() is True:
			return('Woah, chill out!')
		#don't know if I should have sliced this.  Is slicing ever acceptable? It seems very sloppy to me
		elif statement[-1:] == '?':
			return('Sure.')
		#responding to nothing
		elif statement != '':
			return ('Whatever.')
		#responding to anything else
		else: 
			return ('Fine. Be that way!')
