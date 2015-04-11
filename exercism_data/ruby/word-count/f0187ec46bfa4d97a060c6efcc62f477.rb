class Phrase
  def initialize(sentence)
    @words = sentence.downcase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object(tally) do |word, tally|
      tally[word] += 1
    end
  end

  private

  def tally
    Hash.new(0)
  end
end
