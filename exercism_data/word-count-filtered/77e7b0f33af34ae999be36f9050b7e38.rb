class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/\W+/)
    words.reduce({}) do |result, word|
      result[word] = result.fetch(word, 0) + 1
      result
    end
  end
end
