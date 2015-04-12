class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def word_count
    count_each_of(words_in_the_phrase)
  end

  private

  def count_each_of(words)
    words.each_with_object(Hash.new(0)) { |word,counts| counts[word] += 1 }
  end

  def words_in_the_phrase
    @phrase.downcase.scan(/\w+/)
  end
end
