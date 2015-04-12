Phrase = Struct.new(:phrase) do
  def word_count
    histogram = Hash.new {|hash, word| hash[word] = 0}
    sanitized_words.each_with_object(histogram) { |word, acc| acc[word] += 1 }
  end

  def sanitized_words
    phrase.downcase.scan(/[\w']+/)
  end
end
