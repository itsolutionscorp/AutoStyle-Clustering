class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(tally) do |word, tally|
      tally[word] += 1
    end
  end

  private

  def words
    @phrase.downcase.scan(/\w+/)
  end

  def tally
    Hash.new(0)
  end
end
