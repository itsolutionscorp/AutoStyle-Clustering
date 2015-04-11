class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    h = Hash.new(0)
    all_words.each_with_object(h) do |word, distinct_words|
      distinct_words[word] += 1
    end
  end

  def all_words
    text.downcase.scan /\w+/
  end

end
