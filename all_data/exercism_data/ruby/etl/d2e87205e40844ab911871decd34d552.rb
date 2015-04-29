class ETL
  
  def self.transform(old)
    old.each_with_object({}) do |(key, value), hash|
      value.collect {|letters| hash[letters.downcase] = key }
    end
  end
end
