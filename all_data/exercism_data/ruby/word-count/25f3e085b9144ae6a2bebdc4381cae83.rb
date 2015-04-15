class Phrase
  def initialize(text)
    @text = text.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] +=  1
    end
  end

  private
  def words
    text.scan(/\w+/)
  end

  def text
    @text
  end
end
