class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def words
    text.downcase.gsub(/[^0-9a-z ]/, '').split(" ")
  end

  def word_count
    counts = Hash.new(0)
    words.each { |word| counts[word] +=1 }
    counts
  end
end
