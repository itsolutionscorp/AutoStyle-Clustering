class Phrase
  attr_accessor :text

  def initialize(text)
    @text = text
  end

  def words
    text.downcase.scan(/\w+\'?\w+|\d+/)
  end

  def word_count
    counts = Hash.new(0)
    words.each{ |word| counts[word] += 1 }

    counts
  end

end
