class Integer

	def divisible?(num)
		self % num == 0
	end

end

class Raindrops

	def self.convert(num)
		string = ""
		string += "Pling" if num.divisible?(3)
		string += "Plang" if num.divisible?(5)
		string += "Plong" if num.divisible?(7)
		string += num.to_s if string.empty?
		string
	end

end
