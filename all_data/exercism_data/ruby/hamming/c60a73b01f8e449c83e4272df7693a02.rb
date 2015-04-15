class Hamming 
	def self.compute (x,y)
		a = 0;
		min = [x.length,y.length].min
		for i in 0...min 
			if x[i]!=y[i]
				a += 1
			end
		end
		return a
	end
end	
