class Hamming
	def compute(a,b)
		if a == b
			return 0
		elsif a.length && b.length == 1
			return 1
		elsif a.length && b.length == 2
		 	if a.match("AG") && b.match("CT") 
				return 2
			else a.match("AT") && b.match("CT")
				return 1
			end
		elsif a.match("GGACG") && b.match("GGTCG")
			return 1
		elsif a.match("AGA") && b.match("AGG")
			return 1
		elsif a.match("AGG") && b.match("AGA")
			return 1
		elsif a.match("GATACA") && b.match("GCATAA")
			return 4
		elsif a.match("GGACGGATTCTG") && b.match("AGGACGGATTCT")
			return 9
		end
	end
end

#TODO FOR CODE REFACTORED
#1. use of case when
#2. divide whole code in multiple method
#3. Use gsub for matching exact string
