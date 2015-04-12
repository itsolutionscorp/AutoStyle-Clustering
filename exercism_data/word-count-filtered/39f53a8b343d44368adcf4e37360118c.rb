class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase.scan(/\w+/).map(&:downcase)
  end

  def word_count
    phrase.inject(Hash.new(0)) do |word_count, word|
      word_count[word] += 1; word_count
    end
  end
end
