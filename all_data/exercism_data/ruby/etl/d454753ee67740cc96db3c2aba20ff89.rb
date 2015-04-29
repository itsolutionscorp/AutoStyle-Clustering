class ETL
	def self.transform(to_be_tranformed)
		new_tranformation = Hash.new
		to_be_tranformed.each do |key,value| 
			value.each do |val|
				new_tranformation[val.downcase] = key
			end
		end
		return new_tranformation
	end
end
