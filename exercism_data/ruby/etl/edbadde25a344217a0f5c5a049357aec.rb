class ETL
  def self.transform(old_store)
    new_store = Hash.new

    old_store.each do |old_key, old_value|
      old_value.each do |value|
        new_key = value.downcase
        new_store[new_key] = old_key
      end
    end
    new_store
  end
end
