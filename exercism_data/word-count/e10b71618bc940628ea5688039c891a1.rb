class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
  	split_phrase
  	count_words
  end

  private

  def split_phrase
    @phrase.downcase!
    @words = @phrase.scan(/\w+/)
  end

  def count_words
    @count = Hash.new(0)
    @words.each do |word|
      @count[word] += 1
    end
    @count
  end

end
