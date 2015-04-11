class Phrase
  
  attr_accessor :phrase
 
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count()
    count = Hash.new(0)
    phrase_array = phrase.downcase.gsub(/[^a-z0-9\'\s]/, ' ').split(/[\s,]+/)
    phrase_array.each do |v|
      count[v] += 1
    end
    return count
  end

end
