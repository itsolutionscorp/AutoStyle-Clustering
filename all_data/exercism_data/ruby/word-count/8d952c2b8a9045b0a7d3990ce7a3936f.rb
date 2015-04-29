class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/\W+/)
    words.reduce(Hash.new(0)) do |result, word|
      result[word] += 1
      result
    end
  end
end
