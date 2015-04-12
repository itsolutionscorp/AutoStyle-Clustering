class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = Hash.new(0)
    phrase.downcase.split(/[^\w']/).each do |word| 
      @word_count[word] += 1 unless word.empty?
    end
  end
end
