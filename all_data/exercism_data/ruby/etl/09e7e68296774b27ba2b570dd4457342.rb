class ETL
  def self.transform(old_scores)
    old_scores.each_with_object({}) { |(score, letters), hash|
      letters.each do |letter|
        hash[letter.downcase] = score
      end
    }
  end
end
