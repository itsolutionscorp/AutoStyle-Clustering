class ETL
  def transform(old)
    data = {}
    old.each do |k,v|
    	v.each do |value|
    		data[value.downcase] = k
    	end
    end
    return data
  end
end
