class Raindrops
	def self.convert(arg)
		res=''
		aux=arg
		while arg%2 == 0 
			arg = arg/2
		end

		flag=1
		while arg%3 == 0 
			arg = arg/3
			if flag == 1
				res = res + 'Pling'
				flag=0
			end
		end
		flag=1

		while arg%5 == 0 
			arg = arg/5
			if flag == 1
				res = res + 'Plang'
				flag=0
			end
		end
		flag=1
			
		while arg%7 == 0 
			arg = arg/7
			if flag == 1
				res = res + 'Plong'
				flag=0
			end
		end
		flag=1

		if res == '' 
			return aux.to_s
		end
		return res

	end
end
