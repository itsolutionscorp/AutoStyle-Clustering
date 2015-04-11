class ETL
	def self.transform(old)
		hash = {}
		old.each {|key , value| value.each{ |val| hash[val.downcase] = key}} 
		hash
	end
end
