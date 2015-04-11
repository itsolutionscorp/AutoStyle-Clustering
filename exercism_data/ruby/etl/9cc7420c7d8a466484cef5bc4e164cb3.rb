class ETL
  def self.transform(old)
    new_hash = {}
    old.each do |key, value|
      value.each do |v|
        new_hash[v.downcase] = key
      end
    end
    return new_hash
  end
end
