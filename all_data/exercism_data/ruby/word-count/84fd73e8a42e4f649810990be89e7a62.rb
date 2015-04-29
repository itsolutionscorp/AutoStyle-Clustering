class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    p = Hash.new(0)
    array_of_words.each{ |v| p[v] += 1 }
    p
  end

private

  def array_of_words
    @phrase.scan(/[\w\']+/)
  end

end
