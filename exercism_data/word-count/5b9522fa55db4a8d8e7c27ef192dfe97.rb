class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  attr_reader :phrase

  def word_count
    data = Hash.new(0)

    phrase.downcase.scan(/[\w']+/) do |word|
      data[word] += 1
    end

    data
  end
end
