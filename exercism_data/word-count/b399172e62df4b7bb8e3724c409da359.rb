class Phrase
  attr_reader :word_count

  def initialize(phrase)
    count_words(phrase)
  end

  private

  def count_words(phrase)
    @word_count = Hash.new(0)
    words_for(phrase).each do |word|
      @word_count[word] += 1
    end
  end

  def words_for(phrase)
    phrase.downcase.scan(/\w+/)
  end

end
