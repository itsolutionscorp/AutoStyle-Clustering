class ETL
  def self.transform(values)
    values.each_with_object({}) do |(score, words), out|
      words.each do |word|
        out[word.downcase] = score
      end
    end
  end
end
