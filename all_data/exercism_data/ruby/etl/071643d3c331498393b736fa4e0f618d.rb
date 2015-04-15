class ETL
  def self.transform(legacy_hash)
    new_hash = {} # This seems slightly more readable than "tap"
    legacy_hash.each do |key, values|
      values.each do |value|
        new_hash[value.downcase] = key
      end
    end
    new_hash
  end
end
