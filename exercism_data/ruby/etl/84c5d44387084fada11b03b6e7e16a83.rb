class ETL
  def self.transform (hash)
    result = {}
    hash.each do |key, value|
      value.each do |letter|
        result[letter.downcase] = key
      end
    end
    return result
  end
end
