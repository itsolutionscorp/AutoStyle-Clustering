class Phrase
  attr_reader :word_count

  def initialize(phrase)
    words = split_and_sanitize(phrase)
    @word_count = Hash.new(0)

    words.each { |word| @word_count[word] += 1 }
  end

  private

  def split_and_sanitize(phrase)
    phrase.downcase.gsub(/[^a-z0-9\s]/i, ' ').split(' ')
  end
end
