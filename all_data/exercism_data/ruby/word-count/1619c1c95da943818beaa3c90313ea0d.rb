class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_counts if @word_counts
    @word_counts = Hash.new
    words = @phrase.split(between_words)
    words.each do |word|
      count_word(word.downcase)
    end
    @word_counts
  end
 
  def count_word(word)
    if @word_counts[word]
      @word_counts[word] = @word_counts[word] + 1
    else
      @word_counts[word] = 1
    end
  end

  def between_words
    /\W+/
  end
end
