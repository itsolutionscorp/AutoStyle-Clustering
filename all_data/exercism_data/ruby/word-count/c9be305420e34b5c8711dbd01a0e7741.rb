class Phrase

  def initialize(phrase)
    @words = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    words = Hash.new(0)
    @words.each { |w| words[w] += 1 }
    words
  end

end
