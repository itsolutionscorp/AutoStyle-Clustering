def to_rna(n_seq):
	res = ""

	try:
		# Just for good measure
		n_seq = n_seq.strip()
		
		# We'll use a list to store the sequence as we form it
		# to prevent space issues caused by strings immutability.
		tmp = list()
		for n in n_seq:
			if n == "G":
				tmp.append("C")
			elif n == "C":
				tmp.append("G")
			elif n == "T":
				tmp.append("A")
			elif n == "A":
				tmp.append("U")
			else:	
				# Ideally, this should be handled by the caller
				# so we'll just raise it here
				raise TypeError("This is not a DNA sequence!")
		res = "".join(tmp)
	except AttributeError:
		# We will just let finally return an empty string
		pass
	finally:
		return res
		
