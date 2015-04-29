class Raindrops
	def self.convert(num)
		result_string = ''
		result_string += 'Pling' if pling?(num)
		result_string += 'Plang' if plang?(num)
		result_string += 'Plong' if plong?(num)
		result_string += num.to_s if result_string == ''
		result_string
	end

	def self.pling?(num)
		num % 3 == 0
	end

	def self.plang?(num)
		num % 5 == 0
	end

	def self.plong?(num)
		num % 7 == 0
	end
end
