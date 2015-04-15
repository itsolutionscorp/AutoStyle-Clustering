class ETL
  def self.transform(hash)
    new_hash = Hash.new
    hash.each do |key, value|
      value.each { |element| new_hash[element.downcase] = key }
    end
    new_hash
  end
end
