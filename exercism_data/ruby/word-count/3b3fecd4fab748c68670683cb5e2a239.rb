class Phrase
  attr_accessor :word_count

  def initialize(words)
    @word_count = Hash.new(0)
    words.downcase.split(/\W/).each do |w|
      @word_count[w] += 1 unless w.empty?
    end
  end
end
