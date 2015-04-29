class Phrase

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end


  private

  def words
    text.downcase.scan(/\w+/)
  end
end
