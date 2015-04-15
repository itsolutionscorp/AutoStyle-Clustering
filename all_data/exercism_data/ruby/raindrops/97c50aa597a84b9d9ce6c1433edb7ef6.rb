class Raindrops

	def self.convert(num)
		initial = num
		prime_factors = []
		result = []
		counter = 2
		100.times do 
			if num%counter == 0
				prime_factors << counter
				num /= counter
				counter = 1
			end
			counter += 1
		end
		prime_factors.uniq.each do |i|
			if i == 3
				result << "Pling"
			elsif i == 5
				result << "Plang"
			elsif i == 7
				result << "Plong"
			end
		end
	result << initial if result.length == 0
	result.uniq.join('')
	end
end
