class ETL 

	def self.transform(hash)
		hash.each.inject({}) do |new_hash, (key, value)| 
			value.to_a.each {|letter| new_hash[letter.downcase] = key}
			new_hash
		end	
	end

=begin
	def self.transform(hash)
		new_hash = {}
		hash.each do |key, value| 
			value.to_a.each {|letter| new_hash.merge!({letter.downcase => key})}
		end	
		new_hash
	end
=end
end
