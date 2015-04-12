class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    each_word.with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end

  private

  def each_word
    phrase.scan(/[\w']+/).to_enum
  end
end
