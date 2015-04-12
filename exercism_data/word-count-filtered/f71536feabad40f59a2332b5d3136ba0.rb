class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.scan(/\w+/).each_with_object({}) { |token, words|
      word = token.downcase
      words[word] ||= 0
      words[word] += 1
    }
  end

  private

  attr_reader :phrase
end
