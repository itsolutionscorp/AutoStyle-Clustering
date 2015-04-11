class ETL

  def self.transform(old)
    old.each_with_object({}) do |(score, words), result|
      words.each do |word|
        result[word.downcase] = score
      end
    end
  end

end
