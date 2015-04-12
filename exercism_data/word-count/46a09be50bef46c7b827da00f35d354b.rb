class Phrase
  attr_accessor :text

  def initialize(text)
    @text = text
  end

  def word_count
    counts = Hash.new(0)

    words.each do |word|
      counts[word] += 1
    end

    counts
  end

  def words
    text.downcase.scan(/\w+\'?\w+|\d+/)
  end

end
