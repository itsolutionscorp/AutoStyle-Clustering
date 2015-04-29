class ETL

  def self.transform(key_words)
    key_words.each_with_object({}) do |(key, words), transformation|
      words.each do |word|
        transformation[word.downcase] = key
      end
    end
  end

end
