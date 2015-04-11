class ETL

  def self.transform(old_hash)
    new_hash = {}
    old_hash.each_pair do |score, words|
      words.each{|word| new_hash[word.downcase] = score}
    end
    new_hash
  end
end
