class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    @word_count = Hash.new(0)
    normalized_words.each{|w| @word_count[w] += 1}
    @word_count
  end

  private
  def normalized_words
    @words.downcase.split(non_word_characters)
  end

  def non_word_characters
    /[\W]+/
  end
end
