class Raindrops
	def self.convert(num)
		result=""
		if num % 3 == 0 then
			result=result + 'Pling'
		end
		if num % 5 == 0 then
			result=result + 'Plang'
		end
		if num % 7 == 0 then
			result=result + 'Plong'
		end
		if result == "" then
			result = num
		end
		return result.to_s

	end
end
