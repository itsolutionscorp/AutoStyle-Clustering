class Phrase
  attr_reader :word_count
  
  def initialize(phrase)
    @word_count = Hash.new(0)
    count(phrase)  
  end

  private
  def count(phrase)
    phrase.scan(/[a-z]+[\-\']?[a-z]*|[0-9]+/i).each { |word| @word_count[word.downcase] += 1 }
  end
end
