class Phrase
  attr_accessor :word_count
  def initialize(words)
    self.word_count = Hash.new(0)
    count(words)
  end

  private

  def count(words)
    words.split(non_word_characters).each{|w| word_count[w.downcase] += 1}
  end

  def non_word_characters
    /[^\w]+/
  end
end
