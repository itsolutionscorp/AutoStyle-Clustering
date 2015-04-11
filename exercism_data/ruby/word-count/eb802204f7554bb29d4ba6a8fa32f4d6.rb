class Phrase

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  private

  def words
    text.downcase.scan(/\w+/)
  end
end
