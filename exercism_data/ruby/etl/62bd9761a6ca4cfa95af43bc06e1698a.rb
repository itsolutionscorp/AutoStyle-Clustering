class ETL

  def self.transform(old_key)
    old_key.each_with_object({}) do |(key,value), new_key|
      reverse_order(new_key, value, key)
    end
  end

  def self.reverse_order(store, new_keys, value)
      new_keys.each do |key|
        store[key.downcase] = value
      end
    end
end
