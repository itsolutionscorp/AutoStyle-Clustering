class Raindrops
	def self.convert(num)
		out = ""
		if num % 3 == 0
			out = out + "Pling"
		end
		if num % 5 == 0
			out = out + "Plang"
		end
		if num % 7 == 0
			out = out + "Plong"
		end
		if out == ""
			out = "%d" % num
		end
		return out
	end
end
