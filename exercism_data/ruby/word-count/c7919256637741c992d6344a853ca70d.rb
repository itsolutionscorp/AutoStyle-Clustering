class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = phrase.downcase.gsub(',', ' ').gsub(/[^a-z0-9'\s]/, '').split(' ')
  end

  def word_count
    Hash[words.uniq.map { |word| [word, words.count(word)] }]
  end
end
