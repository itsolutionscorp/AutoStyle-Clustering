class Hamming
	def self.compute(*s)
		(0...s.map{|l| l.length}.min).select{|i| s[0][i] != s[1][i]}.length
	end
end
		
	
