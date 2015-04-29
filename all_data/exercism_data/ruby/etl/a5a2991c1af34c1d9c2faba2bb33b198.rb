class ETL
  def self.transform(hash)
    {}.tap do |new_hash|
      hash.each do |score, letters_arr|
        letters_arr.each do |letter|
          new_hash[letter.downcase] = score
        end
      end
    end
  end
end
