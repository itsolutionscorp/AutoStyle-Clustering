class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @words ||= phrase.downcase.scan(/[a-z]+|\d+/)
  end

  def word_count
    words.each_with_object({}) do |word, result|
      result[word] = result[word].to_i + 1
    end
  end
end
