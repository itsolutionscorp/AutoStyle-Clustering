class Raindrops
	def self.convert num
		factors = (1..num).select { |n|num % n == 0}
		answer = ''
		if factors.include? 3
			answer += "Pling"
		end
		if factors.include? 5
			answer += "Plang"
		end
		if factors.include? 7
			answer += "Plong"
		end
		return answer unless answer.eql?('')
		num.to_s
	end
end
