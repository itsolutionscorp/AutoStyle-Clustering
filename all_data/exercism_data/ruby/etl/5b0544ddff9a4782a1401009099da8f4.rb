class ETL
  def self.transform(old_hash)
    invert_hash_key_list(old_hash)
  end

  private

  def self.invert_hash_key_list(old_hash)
    old_hash.each_with_object({}) do |(key, list_values), new_hash|
      list_values.each { |value| new_hash[value.downcase] = key }
    end
  end
end
