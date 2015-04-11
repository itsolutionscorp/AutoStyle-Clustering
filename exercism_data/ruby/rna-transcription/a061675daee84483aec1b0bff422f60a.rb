class Complement
	def self.of_dna(x)
		xArray=x.split("")
		xArray.each { |y|
			case y
			when "A" then r="U"
		       	when "T" then r="A"
			when "C" then r="G"
			when "G" then r="C"
			end
			y.gsub!(y,r)
		}
		return xArray.join
	end
	def self.of_rna(x)
		xArray=x.split("")
		xArray.each { |y|
			case y
			when "U" then r="A"
			when "A" then r="T"
			when "C" then r="G"
			when "G" then r="C"
			end
		       	y.gsub!(y,r)
		}
		return xArray.join
	end
end
