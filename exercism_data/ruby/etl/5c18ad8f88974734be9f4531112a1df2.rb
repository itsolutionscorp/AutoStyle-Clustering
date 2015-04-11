class ETL

  def self.transform(hash)
    results = Hash.new { |k, v| k[v] = v }
    hash.each { |key, value| value.each { |val| results[val.downcase] = key } }
    results
  end

end
