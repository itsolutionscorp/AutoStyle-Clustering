class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.scan(/\w+/)
    words.each_with_object({}) do |word, result|
      result[word] = result.fetch(word, 0) + 1
    end
  end
end
