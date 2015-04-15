import re,codecs
			

class Bob(object):
	def hey(self, grt):
		
		#specs
		question = lambda w: w=='?'
		yelling  = lambda w: w.isalpha and ( w.isupper() or w.isdigit() )
		nothing  = lambda w: not w or w.isspace()
		# -- dict
		#rkey = (question=0, yelling=0, nothing=0)
		response = {(1,0,0) : 'Sure.', 
			        (0,1,1) : 'Woah, chill out!', 
			        (1,1,0) : 'Sure.', 
			        (1,0,1) : 'Sure.', 
			        (1,1,1) : 'Woah, chill out!', 
			        (0,0,0) : 'Fine. Be that way!', 
		   		 }


		#string input
		wwords = grt.split()
		specialchars = ['%','^','*','@','#','$','(','!',',','.']
		words = []
		for w in wwords:
			for ww in w:
				if ww not in specialchars:
					words.append(ww)
		print('words ', words)
		# - nothing
		if all( [nothing(w) for w in words] ):
			return response[(0,0,0)]
		w = words[-1:]
		ww = w[-1]
		print'??? ', ww[-1]


		#multi-conditions
		# - question 
		sure = question( ww[-1]) 
		# - yell
		yell = [ yelling(w) for w in words[:-1] ]
		digits = [ w.isdigit() for w in words]
		woah = all(yell)
		# - special cases
		if all(digits): 
			return 'Whatever.'
		if any(digits) and woah and sure:
			# -- 1234? -> question 1,1 ? ?
			woah2=False
			sure2 = not sure
		else:
			# -- THINK? -> yell    1,1 ? ?
			woah2 = True
		# - hash
		hashkey = (sure, woah, woah2)
		print 'hashkey ',hashkey
		if hashkey in response:
			return response[hashkey]
		else: 
			return 'Whatever.'
