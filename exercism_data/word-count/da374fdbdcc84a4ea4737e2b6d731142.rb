class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    split_phrase.each_with_object(Hash.new(0)) { |word, hash| hash[word.downcase] += 1 }
  end

  private

  attr_reader :phrase

  def split_phrase
    phrase.scan(/[\w']+/)
  end

end
