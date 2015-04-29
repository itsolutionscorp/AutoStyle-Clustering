class ETL

  def self.transform(old_format={})
    old_format.inject({}) do |new_format, subset|
      accumulate_reformated(subset, new_format)
    end
  end

  def self.accumulate_reformated(subset, new_format)
    key, values = subset
    new_format.merge! map_array_of_values_to_key(key, values)
  end

  def self.map_array_of_values_to_key(key, values)
    values.inject({}) do |hash, value| 
      hash[value.downcase] = key
      hash
    end
  end  

end
