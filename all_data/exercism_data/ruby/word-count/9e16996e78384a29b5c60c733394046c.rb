class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def convert_text_to_words
    text.downcase.split(/[^0-9a-z]+/)
  end

  def word_count
    counts = Hash.new(0)
    words.each { |word| counts[word] +=1 }
    counts
  end
end
