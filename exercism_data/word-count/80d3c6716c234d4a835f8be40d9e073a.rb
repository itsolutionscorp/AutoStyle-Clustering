class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/\W+/)
    words.reduce({}) do |result, word|
      result.key?(word) ? result[word] += 1 : result[word] = 1
      result
    end
  end
end
