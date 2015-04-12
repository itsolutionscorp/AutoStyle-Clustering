class Phrase

  attr_reader :phrase, :words
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words = phrase.scan(/[\w']+/)
    Hash[words.map { |word| [word, words.count(word)] }]
  end

end
