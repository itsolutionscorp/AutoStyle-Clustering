class Phrase

  def initialize(phrase)
    @phrase = phrase.scan(/[\w']+/)
  end
  
  def word_count
    frequency = Hash.new(0)
    @phrase.each { |word| frequency[word.downcase] += 1 }
    frequency
  end
  
end
