class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    split_phrase.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

private
  def split_phrase
    phrase.downcase.split(/\W/).reject { |word| word.empty? }
  end

end
