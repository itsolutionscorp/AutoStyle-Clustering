class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @word_count = Hash.new(0)
    phrase.downcase.split(/\W/).each do |w|
      @word_count[w] += 1 unless w.empty?
    end
  end
end
