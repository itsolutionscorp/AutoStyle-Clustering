class ETL

  def self.transform(old)
  	hash = {}
  	old.each do |key, value|
  	  value.each do |x|
  		hash[x.downcase] = key
  	  end
  	end
  	hash
  end

end
