class Phrase
  attr_reader :phrase
  attr_accessor :word_frequency

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_frequency ||= calculate_frequency
  end

  def calculate_frequency
    word_frequency = Hash.new(0)
    word_array.each do |word|
      word_frequency[word] += 1
    end
    word_frequency
  end

  private
  def word_array
    phrase.downcase.scan(/\w+/)
  end
end
