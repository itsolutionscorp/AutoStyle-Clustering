module ETL
  extend self

  def transform(value_to_words)
    value_to_words.each_with_object Hash.new do |(value, words), word_to_value|
      words.each { |word| word_to_value[word.downcase] = value }
    end
  end
end
