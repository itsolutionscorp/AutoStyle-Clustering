class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_to_count = words_from @phrase
    words_to_count.uniq.each_with_object(Hash.new) do |word,counted_words|
      counted_words[word] = words_to_count.count(word)
    end
  end

  def remove_clutter phrase
    phrase.downcase.scan(/\w+/)
  end

  def words_from phrase
    remove_clutter(phrase)
  end

end
