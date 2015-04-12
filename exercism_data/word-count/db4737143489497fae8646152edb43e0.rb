class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    frequency = Hash.new(0)
    string_to_array.each do |word|
      frequency[word] += 1
    end
    frequency
  end

  def string_to_array
    phrase.downcase.scan(/\w+/)
  end
end
