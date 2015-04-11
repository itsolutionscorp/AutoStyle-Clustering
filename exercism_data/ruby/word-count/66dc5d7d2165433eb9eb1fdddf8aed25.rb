class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    split_string
    count_words
  end

  private

  def split_string
    @phrase.downcase!
    @array = @phrase.scan(/\w+/)
  end

  def count_words
    @words = Hash.new(0)
    @array.each do |word|
      @words[word] += 1
    end
    @words
  end

end
