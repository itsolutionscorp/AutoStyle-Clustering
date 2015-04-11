class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, word_counter|
      word_counter[word] +=1
    end
  end

  private

  def words
    text.downcase.split(/[^0-9a-z']+/)
  end
end
