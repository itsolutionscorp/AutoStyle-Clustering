class Phrase
  def initialize(text)
    @text = text
  end

  private
  attr_reader :text

  def words
    text.downcase.split(/\W+/)
  end

  def word_count
    words.reduce Hash.new(0) do |acc, word|
      acc[word] += 1
      acc
    end
  end
end