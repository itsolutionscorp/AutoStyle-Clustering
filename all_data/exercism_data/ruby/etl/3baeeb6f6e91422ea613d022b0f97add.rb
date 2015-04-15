class ETL
	def self.transform(old)
	  values_per_letter = {}
		old.each do |value, letters|
			letters.each	do |letter|				
			  values_per_letter.store(letter.downcase, value) if values_per_letter[letter.downcase].nil?
			end
		end
		values_per_letter		  
	end	
end
