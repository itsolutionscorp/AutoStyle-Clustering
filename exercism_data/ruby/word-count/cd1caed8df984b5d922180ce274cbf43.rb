class Phrase
  
  def initialize(message)
  	@message = message  
  end	

  def word_count  	
    tokenize().each_with_object({}) do |word, hash| 
      hash.include?(word) ? hash[word] += 1 : hash[word]=1
    end    
  end

  private    
    def tokenize
      @message.downcase.scan(/\w+/)
    end	
end
