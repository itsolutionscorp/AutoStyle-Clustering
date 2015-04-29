class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end

  private

  def words
    @words ||= @phrase.scan(/\w+/)
  end
end
