class Phrase

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    word_counts = Hash.new(0)
    text.downcase.scan(/\w+/) { |word| word_counts[word] += 1 }
    word_counts
  end
end
