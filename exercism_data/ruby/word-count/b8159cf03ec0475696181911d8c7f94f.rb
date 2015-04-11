class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s.downcase
  end

  def word_count
    counter = Hash.new(0)
    phrase.scan(/[a-zA-Z0-9]+/).each do |word|
      counter[word] += 1
    end
    counter
  end
end
