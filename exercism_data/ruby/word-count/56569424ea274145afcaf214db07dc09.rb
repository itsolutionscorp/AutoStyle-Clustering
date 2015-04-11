class Phrase
  
  def initialize string
    @string = string
  end
  
  def word_count
    @string.split(/[^\w]+/).inject(Hash.new(0)) do |hash, word| 
      word = word.downcase
      hash[word] += 1
      hash 
    end
  end
  
end
