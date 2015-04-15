class Raindrops
	def self.convert(number)
		flag = false
		string = ''
		if(number % 3 == 0)
			string += 'Pling'
			flag = true
		end
		if(number % 5 == 0)
			string += 'Plang'
			flag = true
		end
		if(number % 7 == 0)
			string += 'Plong'
			flag = true
		end

		if(flag)
			string
		else
			number.to_s
		end
	end
end
