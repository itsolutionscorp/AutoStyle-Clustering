class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/\W|\s/).reject { |word| word =~ /\A\Z/ }
    Hash[ words.map { |word| [word, words.count(word)] } ]
  end

end
