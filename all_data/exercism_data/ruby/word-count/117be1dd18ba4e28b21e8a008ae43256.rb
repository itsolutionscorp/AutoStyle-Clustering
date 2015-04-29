class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_counts if @word_counts
    @word_counts = Hash.new(0)
    words = @phrase.split(between_words)
    words.each do |word|
      @word_counts[word.downcase] += 1
    end
    @word_counts
  end
 
  private

  def between_words
    /\W+/
  end
end
