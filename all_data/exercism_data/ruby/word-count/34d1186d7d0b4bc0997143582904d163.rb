class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= count_words
  end

  private

  def count_words
    @word_count = Hash.new(0)
    @phrase.scan(/\w+/) do |word|
      @word_count[word.downcase] += 1
    end
    @word_count
  end

end
