class ETL

  def self.transform(old)
    old.each_with_object({}) do |(key, value), hash| 
      value.collect {|words| hash[words.downcase] = key}   
    end
  end
  
end
