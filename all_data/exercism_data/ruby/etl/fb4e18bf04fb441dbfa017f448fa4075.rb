class ETL
  
  def self.transform values
    values.each_pair.with_object({}) do |(score, words), result|
      words.collect do |word|
        result[word.downcase] = score
      end
    end
  end
  
end
