module ETL

	def self.transform(input)
		Hash[ *input.invert.map { |k,v| k.map { |l| [l.downcase, v] } }.flatten ]
	end
	
end
