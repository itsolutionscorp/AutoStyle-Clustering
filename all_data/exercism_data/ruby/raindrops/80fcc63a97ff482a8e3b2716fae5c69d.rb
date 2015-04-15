class Raindrops
	def self.convert num
		answer = ''
		if num % 3 == 0
			answer += "Pling"
		end
		if num % 5 == 0
			answer += "Plang"
		end
		if num % 7 == 0
			answer += "Plong"
		end
		return answer unless answer.eql?('')
		num.to_s
	end
end
