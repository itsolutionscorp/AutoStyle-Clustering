class Phrase
  attr_accessor:phrase 

  def initialize(phrase="")
    @phrase=phrase
  end

  def word_count
    separated_string=phrase.downcase.scan(/[\w']+/)
    frequency=Hash.new
    separated_string.each do |word|
      frequency[word] = 0
    end
    separated_string.each do |word|
      frequency[word] +=1
    end
      return frequency
  end    
end
