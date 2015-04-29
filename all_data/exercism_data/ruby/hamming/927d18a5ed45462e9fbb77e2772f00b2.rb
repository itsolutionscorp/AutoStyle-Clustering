class Hamming
	def self.compute(arg1,arg2)
			times = arg1.split("").length
			uno = arg1.split("")
			dos = arg2.split("")
			x = 0
			dist = 0

		for x in 0..times
			if uno[x] != dos[x]
				dist = dist +1
			end
		end

		dist
    end    
end

# puts Hamming.compute('gat', 'gct')
