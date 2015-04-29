class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = Hash.new(0)
    phrase.gsub(/[^\w']/, ' ').split.each do |word| 
      @word_count[word.downcase] += 1
    end
  end
end
