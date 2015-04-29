class ETL
	def self.transform(old)
	  value_per_letter = {}
		old.each do |value, letters|
			letters.each { |letter|	 value_per_letter[letter.downcase] = value }
		end
		value_per_letter		  
	end	
end
