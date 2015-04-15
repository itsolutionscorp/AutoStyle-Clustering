class ETL

  def self.transform(old)
    new_hash = {}
    old.each do |key, value|
      value.each do |string|
      new_hash[string.downcase] = key
      end
    end
    new_hash
  end
end
