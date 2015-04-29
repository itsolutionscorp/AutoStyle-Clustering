# my solution
class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = count split(phrase)
  end

  private

  def split(phrase)
    phrase.downcase.scan /[a-z0-9']+/
  end

  def count(words)
    word_count = Hash.new(0)

    words.each do |word|
      word_count[word] += 1
    end

    word_count
  end
end
