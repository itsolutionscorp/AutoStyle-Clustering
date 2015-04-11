# my solution
class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= count words_in_phrase
  end

  private

  def words_in_phrase
    @phrase.downcase.scan /[a-z0-9']+/
  end

  def count(words)
    word_count = Hash.new(0)

    words.each do |word|
      word_count[word] += 1
    end

    word_count
  end
end
