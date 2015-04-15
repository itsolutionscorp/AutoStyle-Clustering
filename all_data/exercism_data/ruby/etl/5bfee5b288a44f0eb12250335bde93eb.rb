class ETL

  def self.transform(old_hash)
    result = {}

    old_hash.keys.each do |key|
      old_hash[key].each do |value|
        result[value.downcase] = key
      end
    end
    result
  end
end
