class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)
    words.each { |word| count[word] += 1 }
    count
  end

  private

  def words
    phrase.split(/\W+/).map(&:downcase)
  end

end
