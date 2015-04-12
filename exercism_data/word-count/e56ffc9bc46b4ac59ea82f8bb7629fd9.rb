class Phrase
  def initialize(phrase)
    @words = phrase.downcase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object({}) do |word, hash|
      hash[word] = count_of(word)
    end
  end

  private

  def count_of(word)
    @words.count(word)
  end
end
