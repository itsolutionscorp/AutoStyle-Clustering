class Phrase
  attr_reader :word_count, :text
  def initialize(text)
    @text = text
    @word_count = Hash.new(0)
    build_count
  end

  private

  def build_count
    text.downcase.scan(/[\w']+/).each { |w| word_count[w] += 1 }
  end
end
