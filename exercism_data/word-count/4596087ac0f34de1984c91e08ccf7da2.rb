class Phrase
  def initialize(input)
    @words = tokenize(input)
  end
  
  def word_count
    counts = Hash.new(0)
    
    @words.each do |word|
      counts[word] += 1
    end
    
    return counts
  end
  
  private
  
  def tokenize(input)
    input.downcase.scan(/\w+/)
  end
end
