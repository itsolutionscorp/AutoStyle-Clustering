class ETL
  
  def self.transform(old_data)
  	old_data.each_with_object({}) do |(key, value), out|
            value.each do |val|
        	transformed_value = val.downcase!
        	out[transformed_value] = key
        end    
  	end
  end
end
