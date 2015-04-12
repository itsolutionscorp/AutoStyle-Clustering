class Phrase
  def initialize(phrase)
    @words = normalize(phrase)
  end

  def word_count
    unique_words.each_with_object({}) do |word, count|
      count[word] = @words.count(word)
    end
  end

  private

  def normalize(phrase)
    phrase.to_s.downcase.scan(/\w+/)
  end

  def unique_words
    @words.uniq
  end
end
