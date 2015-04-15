class Phrase
  attr_reader :phrase

  def initialize(phrase="")
    @phrase = phrase
  end

  def word_count
    word_array = string_to_array(phrase)
    frequency = Hash.new{0}
    word_array.each do |word|
      frequency[word] += 1
    end
    frequency
  end

  def string_to_array(string)
    phrase.downcase.scan(/\w+/)
  end
end
