class ETL
  def self.transform(old_values)
    new_values = {}
    old_values.each do |score, words|
      words.each do |word|
        new_values[word.downcase] = score
      end
    end
    new_values
  end
end
