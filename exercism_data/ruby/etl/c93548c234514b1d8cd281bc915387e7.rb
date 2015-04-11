class ETL
  def self.transform(old_data)
    old_data.each_with_object({}) do |(score, words), data|
      words.each { |word| data[word.downcase] = score }
    end
  end
end
