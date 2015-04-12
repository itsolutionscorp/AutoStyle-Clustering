class Phrase
  def initialize(input)
    @words = tokenize(input)
  end
  
  def word_count    
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
  
  private
  
  def tokenize(input)
    input.downcase.scan(/\w+/)
  end
end
