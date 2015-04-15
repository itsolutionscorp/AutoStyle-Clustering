class ETL

  def self.transform(legacy_hash)
    new_hash = {}
    legacy_hash.keys.each do |key|
      legacy_hash[key].each do |value|
        new_hash[value.downcase] = key
      end
    end
    new_hash
  end

end
