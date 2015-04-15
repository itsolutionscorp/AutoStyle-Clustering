class ETL
  def self.transform(hash)
    new_hash = {}
    hash.each do |key, value|
      value.each do |letter|
        new_hash[letter.downcase] = key
      end
    end
    new_hash
  end
end
