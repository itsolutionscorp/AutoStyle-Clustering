class ETL
  
  class << self
    
    def transform(old_data)
      old_data.keys.each_with_object({}) do |score,new_data| 
        scores_by_letter = new_hash_using_keys_and_value(old_data[score], score)
        new_data.merge!(scores_by_letter)
      end
    end

    private

    def new_hash_using_keys_and_value(new_keys, value)
      new_keys.each_with_object({}) do |key, new_hash| 
        new_hash[key.downcase] = value 
      end
    end

  end

end
