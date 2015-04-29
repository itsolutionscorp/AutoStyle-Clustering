class ETL

  def self.transform(old_scoring_hash)
    old_scoring_hash.each_with_object({}) do |(score, letters), scoring_hash|
      letters.each do |letter|
        scoring_hash[letter.downcase] = score
      end
    end
  end

end
