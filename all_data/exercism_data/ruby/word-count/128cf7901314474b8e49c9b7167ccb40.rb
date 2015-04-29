class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words.inject(Hash.new(0)) do |accumulator, word|
      accumulator[word] += 1
      accumulator
    end
  end

  private

  def words
    @words ||= begin
      phrase_without_non_alnum = @phrase.gsub /[^\w\s]/, ''
      words = phrase_without_non_alnum.split ' '
      normalized_words = words.map &:downcase
    end
  end
end
