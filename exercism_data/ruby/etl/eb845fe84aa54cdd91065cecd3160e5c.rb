class ETL
  def self.transform(hash)
    new_hash = Hash.new
    hash.each_pair do |score, letters|
      letters.each do |letter|
        new_hash[letter.downcase] = score
      end
    end
    new_hash
  end
end
