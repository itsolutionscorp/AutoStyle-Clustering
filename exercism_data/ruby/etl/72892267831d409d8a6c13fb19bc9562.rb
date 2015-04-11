class ETL
	def initialize
	end

	def self.transform(word)
		word.each {|k,v| word[k] = v.join.downcase }.invert
	end

end
