class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.scan(/\w+/).each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word.downcase] += 1
    end
  end
end
