class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @phrase.scan(/\w+'?\w?/)
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, counts| counts[word.downcase] += 1 }
  end
end
