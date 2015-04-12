class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.scan(/\w+/)
    words.inject({}) do |result, word|
      result[word] ||= 0
      result[word] += 1
      result
    end
  end
end
