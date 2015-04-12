class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(between_words)
    @word_counts ||= words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end
 
  private

  def between_words
    /\W+/
  end
end
