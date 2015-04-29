public
	def to_roman
		a = self
		 rome = []
		while a >= 1000
			a -= 1000
			rome.push('M')
		end
		if a >= 900
			a -= 900
			rome.push('CM')
		end
		while a >=500
			a -= 500
			rome.push('D')
		end
		if a >= 400
			a -= 400
			rome.push('CD')
		end
		while a >= 100
			a -= 100
			rome.push('C')
		end
		if a >= 90
			a -= 90
			rome.push('XC')
		end
		while a>= 50
			a -= 50
			rome.push('L')
		end
		if a >= 40
			a -= 40
			rome.push('XL')
		end
		while a >= 10
			a -= 10
			rome.push('X')
		end
		if a >= 9
			a -= 9
			rome.push('IX')
		end
		while a >= 5
			a -= 5
			rome.push('V')
		end
		if a >= 4
			a -= 4
			rome.push('IV')
		end
		while a >= 1
			a -= 1
			rome.push("I")
		end
		
		  return rome.join

	end
