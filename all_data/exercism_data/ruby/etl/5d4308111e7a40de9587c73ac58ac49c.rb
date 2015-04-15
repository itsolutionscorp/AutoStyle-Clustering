class ETL
  def self.transform(old_values)
    old_values.each_with_object({}) do |(score, words), new_values|
      words.each do |word|
        new_values[word.downcase] = score
      end
    end
  end
end
