class Raindrops
	def self.convert(num)
		numstr = ""
		numstr += "Pling" if num.modulo(3).zero?
		numstr += "Plang" if num.modulo(5).zero?
		numstr += "Plong" if num.modulo(7).zero?
		numstr.empty? ? num.to_s : numstr
	end
end
