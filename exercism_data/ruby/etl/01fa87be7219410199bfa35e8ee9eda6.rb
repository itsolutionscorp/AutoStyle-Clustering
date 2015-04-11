class ETL
	def self.transform(old)    
    	score = Hash.new()
    	old.each do |key,value|
    		value.each {|x| score[x.downcase] = key}   
    	end
    	score
  	end
end
