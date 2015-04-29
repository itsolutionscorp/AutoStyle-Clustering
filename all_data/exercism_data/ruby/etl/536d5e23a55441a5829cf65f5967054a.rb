class ETL
  def self.transform(old_hash)
    new_hash = {}
    old_hash.each do |key, value|
      value.each do |letter|
        letter.downcase!
        new_hash[letter] = key
      end
    end
    Hash[new_hash.sort]
  end
end
