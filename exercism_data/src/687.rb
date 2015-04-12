class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_to_count = @phrase.downcase.scan(/\w+/)
    words_to_count.uniq.each_with_object(Hash.new) do |word,counted_words|
      counted_words[word] = words_to_count.count(word)
    end
  end

end
