class Hamming
	def self.compute(a,b,score=0)
		if a.length==0
			return score
		elsif b.length==0
			return score
		else
			a1=a.slice!(0)
			b1=b.slice!(0)
			return compute(a,b,score+compare(a1,b1))
		end
	end

	def self.compare(a,b)
		if a==b
			return 0
		else
			return 1
		end
	end
end
