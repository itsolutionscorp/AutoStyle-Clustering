class Phrase

  attr_reader :words

  def initialize(phrase)
    @words = phrase.downcase.split /\W+/
  end

  def word_count
    words.each_with_object Hash.new(0) do |word, counts|
      counts[word] += 1
    end
  end

end
