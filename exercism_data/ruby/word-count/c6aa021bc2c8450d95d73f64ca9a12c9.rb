class Phrase
  attr_reader :text

  def initialize text
    @text = text.downcase
    @word_count = Hash.new(0)
  end

  def word_count
    words.each { |word| @word_count[word] += 1 } unless @word_count.any?
    @word_count
  end

  private

  def words
    @words ||= text.scan(/\w+\'\w+|\w+/)
  end
end
