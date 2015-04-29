class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object Hash.new(0) do |acc, word|
      acc[word] += 1
    end
  end

  private
  attr_reader :text

  def words
    text.downcase.split(/\W+/)
  end
end
