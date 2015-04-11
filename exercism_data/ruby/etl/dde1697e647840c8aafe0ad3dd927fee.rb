module ETL
	extend self
	def transform hash
		shiny = Hash.new
		hash.each do |k,v|
			v.each do |unscrubed|
				shiny[unscrubed.downcase] = k
			end
		end
		shiny
	end
end
