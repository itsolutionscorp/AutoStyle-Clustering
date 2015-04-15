Phrase = Struct.new(:phrase) do
  def word_count
    sanitized_words.each_with_object({}) { |word, acc| acc[word] = (acc[word] || 0) + 1 }
  end

  def sanitized_words
    phrase.downcase.gsub(/[^[:alnum:]\s']/, ' ').split(" ")
  end
end
