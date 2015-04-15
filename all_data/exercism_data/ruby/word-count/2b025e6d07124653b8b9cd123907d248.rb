class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    tally = Hash.new(0)
    words.each_with_object(tally) do |word, tally|
      tally[word] += 1
    end
  end

  private

  def words
    @words ||= @sentence.downcase.split(/\W+/)
  end
end
