class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words.map(&:downcase).inject(Hash.new(0)) do |accumulator, word|
      accumulator[word] += 1
      accumulator
    end
  end

  private

  def words
    @words ||= @phrase.scan(/\w+/)
  end
end
