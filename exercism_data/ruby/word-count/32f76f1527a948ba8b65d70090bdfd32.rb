class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = phrase.downcase.split(/\W+/)
  end

  def word_count
    word_count = Hash.new
    words.uniq.each do |word|
      word_count[word] = words.count(word)
    end

    word_count
  end

end
