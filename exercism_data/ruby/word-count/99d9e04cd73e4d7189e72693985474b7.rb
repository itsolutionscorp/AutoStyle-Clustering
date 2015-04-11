class Phrase
  def initialize(text)
    @text = text
  end

  def words
    @text.downcase.scan(/\w+/)
  end

  def word_count
    words.each_with_object(Hash.new 0) { |word, memo| memo[word] +=1 }
  end
end
