class ETL
	def self.transform(old)
	  #value_per_letter = {}
		old.each_with_object({}) do |(value, letters), value_per_letter |
			letters.each { |letter|	 value_per_letter[letter.downcase] = value }
		end  
	end	
end
