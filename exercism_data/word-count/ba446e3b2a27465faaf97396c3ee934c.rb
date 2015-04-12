class Phrase
  def initialize(phrase)
    phrase.scan(/[[:word:]']+/, &method(:count))
  end

  def word_count
    @word_count ||= Hash.new(0)
  end

  private

  def count(word)
    word_count[word.downcase] += 1
  end
end
