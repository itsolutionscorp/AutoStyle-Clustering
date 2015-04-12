class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    array_of_words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  attr_reader :phrase

  def array_of_words
    phrase.to_s.downcase.split(/\W+/)
  end

end
