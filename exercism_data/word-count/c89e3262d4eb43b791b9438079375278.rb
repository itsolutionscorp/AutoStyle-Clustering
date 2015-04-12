class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    phrase_into_array(phrase).each do |word|
      counts[word] += 1
    end
    counts
  end

  def phrase_into_array(phrase)
    words = phrase.downcase.split(/\W+/)
  end

end
