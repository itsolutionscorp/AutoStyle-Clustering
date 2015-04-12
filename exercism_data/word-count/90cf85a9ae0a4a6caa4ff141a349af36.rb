class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_counts if @word_counts
    Hash.new(0)
    words = @phrase.split(between_words)
    @word_counts = words.with_each_object(Hash.new(0)) do |word, counts|
      counts[words.downcase] += 1
    end
    @word_counts
  end
 
  private

  def between_words
    /\W+/
  end
end
