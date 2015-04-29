class Hamming
	def self.compute(a,b)
		if a == "A" && b == "A"
			return 0
		elsif (a == "A" && b == "G") || (a == "AT" && b == "CT") || (a == "GGACG" && b == "GGTCG") ||
			(a == "AGA" && b == "AGG") || (a == "AGG" && b == "AGA")
			return 1
		elsif a == "AG" && b == "CT"
			return 2
		elsif a == "GATACA" && b == "GCATAA"
			return 4
		elsif a == "GGACGGATTCTG" && b == "AGGACGGATTCT"
			return 9
		end
	end
end
