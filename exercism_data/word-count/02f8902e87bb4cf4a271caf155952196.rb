class Phrase
  def initialize(input)
    @corpus = input
    
    normalize!
    sanitize!
    tokenize!
  end
  
  def word_count
    counts = Hash.new(0)
    
    @corpus.each do |word|
      counts[word] += 1
    end
    
    return counts
  end
  
  private
  
  def normalize!
    @corpus.downcase!
  end
  
  def sanitize!
    @corpus.gsub!(/[\W]/, ' ')
  end
  
  def tokenize!
    @corpus = @corpus.split(/\s+/)
  end
end
