class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce(Hash.new(0)) do |count, word|
      count[word] += 1
      count
    end
  end

  def words
    text.downcase.scan(/[0-9a-z']+/)
  end
end
