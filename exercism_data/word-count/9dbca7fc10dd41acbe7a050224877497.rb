class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    h = Hash.new(0)
    valid_words.each do |word|
      h[word] += 1
    end
    h
  end

  private

  def valid_words
    @phrase.downcase.scan(/[\w']+/).map do |word|
      word
    end
  end

end
