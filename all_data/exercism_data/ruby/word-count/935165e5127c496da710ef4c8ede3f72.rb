class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.inject({}) do |word_count, word|
      word_count[word] ||= phrase.count(word)
      word_count
    end
  end

  private

  def phrase
    @normalized_phrase ||= normalize(@phrase)
  end

  def normalize(phrase)
    phrase.scan(/\w+/).map {|word| normalize_word(word)}
  end

  def normalize_word(word)
    word.downcase.split(//).select {|char| char =~ /[a-z]|\d/}.join("")
  end
end
