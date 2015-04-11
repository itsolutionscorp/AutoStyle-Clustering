Phrase = Struct.new(:phrase) do
  def word_count
    words = extract_words phrase

    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] = counts[word] + 1
    end
  end

  private

  def extract_words(phrase)
    phrase.downcase.scan(/\w+/)
  end
end
