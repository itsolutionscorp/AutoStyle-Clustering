class Phrase
  def initialize(text)
    @text = text.downcase
  end

  def word_count
    separate_words.each_with_object({}) do |word, counter|
      counter[word] ||= 0
      counter[word] +=  1
    end
  end

  private
  def separate_words
    text.scan(/\w+/)
  end

  def text
    @text
  end
end
