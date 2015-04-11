class Phrase
  
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    freqs = Hash.new(0)

    cleaned = @phrase.downcase.gsub(/[^a-z0-9 ]/, ' ')
    cleaned.split.each {|word| freqs[word] += 1 }

    freqs
  end

end
