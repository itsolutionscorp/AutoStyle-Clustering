class Phrase
  attr_reader :phrase 

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
      phrase.downcase.scan(/\w+/) do |w|
        counts[w] += 1
      end
      counts
  end
  
end 
