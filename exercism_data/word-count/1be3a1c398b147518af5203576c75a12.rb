class Phrase

  attr_accessor :phrase, :frequency

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    frequency ||= histogram(normalize(phrase))
  end

  def normalize(phrase)
    phrase.downcase.scan(/\w+(?:'\w+)?/)
  end

  def histogram(words)
    words.each_with_object(Hash.new{0}) {|word, hash| hash[word] += 1 }
  end

end
