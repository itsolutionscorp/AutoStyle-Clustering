class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @word_count = Hash.new(0)
    phrase.downcase.scan(/[\w']+/).each do |word|
        @word_count[word] += 1
    end

  end
end
