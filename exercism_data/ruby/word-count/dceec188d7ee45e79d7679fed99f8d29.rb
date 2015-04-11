class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    downcased_words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def downcased_words
    phrase.downcase.split(/\W+/)
  end
end
