class ETL
  def self.transform(hash)   
    hash.each_with_object(Hash.new) do |(score, letters), new_hash|
      letters.each do |letter|
        new_hash[letter.downcase] = score
      end
    end
  end
end
