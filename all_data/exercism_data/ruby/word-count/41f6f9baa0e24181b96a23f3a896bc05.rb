class Phrase
  
  def initialize(message)
  	@message = message  
  end	

  def word_count
  	tokenize().inject({}) do |hash_words, word|  
  	  hash_words.include?(word) ? hash_words[word] += 1 : hash_words[word]=1
      hash_words
    end    
  end

  private    
    def tokenize
      @message.downcase.scan(/\w+/)
    end	
end
