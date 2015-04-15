Phrase = Struct.new(:words) do
  def word_count
    clean_words.each_with_object(Hash.new(0)) {|word, count| count[word] += 1}
  end

  private

  def clean_words
    words.downcase.split(/\W+/)
  end
end
