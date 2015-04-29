class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    words = phrase_into_array(phrase)
    words.each do |word|
      counts[word] += 1
    end
    counts
  end

  def phrase_into_array(phrase)
    words = Array.new
    words = phrase.downcase.split(/\W+/)
    words
  end

end
