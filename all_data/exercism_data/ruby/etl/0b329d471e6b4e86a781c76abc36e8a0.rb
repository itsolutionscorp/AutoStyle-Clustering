class ETL
  def self.transform(old_store)
    new_store = Hash.new

    old_store.each do |old_key, old_value|
      old_values_index = 0
      while old_values_index < old_value.length do
        new_key = old_value[old_values_index].downcase
        new_store[new_key] = old_key
        old_values_index += 1
      end
    end
    new_store
  end
end
