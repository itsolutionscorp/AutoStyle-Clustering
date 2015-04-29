class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    @word_count ||= count
  end

  private

  def count
    @word_count = Hash.new(0)

    @text.scan(/\w+/).each do |word|
      @word_count[word.downcase] += 1
    end

    @word_count
  end
end
