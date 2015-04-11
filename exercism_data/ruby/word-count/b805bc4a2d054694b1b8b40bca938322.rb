class Phrase
  def initialize text
    @text = text.downcase
  end

  def word_count
    words.each_with_object Hash.new(0) do |word, accumulator|
      accumulator[word] += 1
    end
  end

  private
  attr_reader :text

  def words
    text.scan /\w+/
  end
end
