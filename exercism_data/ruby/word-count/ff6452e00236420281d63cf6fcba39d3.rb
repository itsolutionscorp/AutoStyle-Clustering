class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_array.each_with_object(Hash.new(0)) do |word, frequency_count|
      frequency_count[word] += 1
    end
  end

  private
  def word_array
    phrase.downcase.scan(/\w+/)
  end
end