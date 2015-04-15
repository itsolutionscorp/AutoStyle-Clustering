class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @word_count = Hash.new(0)
    words(phrase).each { |w| @word_count[w] += 1 unless w.empty? }
  end

  private

  def words(phrase)
    phrase.downcase.split(/\W/)
  end
end
