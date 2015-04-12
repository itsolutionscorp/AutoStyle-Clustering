class PhraseWordCounter

  attr_reader :words

  def initialize(phrase_input)
    @words = phrase_input.downcase.scan(/[\w']+/)
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, frequency|
    frequency[word.downcase] += 1
    end
  end

end
