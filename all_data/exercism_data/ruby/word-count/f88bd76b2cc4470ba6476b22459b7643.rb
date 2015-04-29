class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.inject(Hash.new(0)) do |h, word|
      h[word] += 1; h
    end
  end

  def words
    @phrase.downcase.scan(/\w+/)
  end

end
