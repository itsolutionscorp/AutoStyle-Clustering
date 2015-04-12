class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    seperated_word.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  private

  def seperated_word
    phrase.downcase.scan(/[\w']+/)
  end

end
