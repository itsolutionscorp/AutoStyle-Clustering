class Phrase
  attr_reader   :text

  def initialize(text)
    @text = text.downcase
  end

  def word_count
    counter = {}
    separate_words.each do |word|
      counter[word] ||= 0
      counter[word] += 1
    end
    counter
  end

  def separate_words
    text.split(/[\W]+/)
  end
end
