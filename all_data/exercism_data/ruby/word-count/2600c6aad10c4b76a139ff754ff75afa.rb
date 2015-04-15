Phrase = Struct.new(:phrase) do
  def word_count
    sanitized_words.each_with_object(Hash.new(0)) { |word, histogram| histogram[word] += 1 }
  end

  def sanitized_words
    phrase.downcase.scan(/[\w']+/)
  end
end
