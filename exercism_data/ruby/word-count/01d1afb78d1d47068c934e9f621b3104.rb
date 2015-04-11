class Phrase
  attr_reader :phrase
  attr_accessor :word_frequency

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_frequency ||= calculate_frequency
  end

  private
  def calculate_frequency
    word_array.each_with_object(Hash.new(0)) do |word, frequency_count|
      frequency_count[word] += 1
    end
  end

  def word_array
    phrase.downcase.scan(/\w+/)
  end
end
