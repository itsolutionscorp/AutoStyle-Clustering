class Phrase
  attr_reader :word_count

  def initialize(text)
    @text = text
    count_words
  end

  def count_words
    @word_count = Hash.new(0)
    words.each { |word| @word_count[word.downcase]+=1 }
  end

  def words
    @text.scan(/[\w'0-9]+/i)
  end

end
