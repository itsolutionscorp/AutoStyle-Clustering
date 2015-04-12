class Phrase
  def initialize(phrase)
    @phrase = normalize(phrase)
    @words  = separate_words
  end

  def word_count
    unique_words.each_with_object({}) do |word, count|
      count[word] = @words.count(word)
    end
  end

  private

  def normalize(phrase)
    phrase.to_s.downcase
  end

  def separate_words
    @phrase.scan(/\w+/)
  end

  def unique_words
    @words.uniq
  end
end
