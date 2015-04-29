class Phrase

  attr_reader :phrase
  private :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)

    words.each { |word| count[word] += 1 }

    return count
  end

  private

  def words
    phrase.downcase.scan(/\w+/)
  end
end
