class ETL
  def self.transform(old_hash)
    new_hash = {}
    old_hash.each do |number, letters|
      letters.each do |letter|
        new_hash[letter.downcase] = number
      end
    end

    return new_hash

  end
end
