class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end 

  def words
    @phrase.scan(for_words).map { |word| word.downcase }
  end

  private

  def for_words
    /\w+/
  end
end
