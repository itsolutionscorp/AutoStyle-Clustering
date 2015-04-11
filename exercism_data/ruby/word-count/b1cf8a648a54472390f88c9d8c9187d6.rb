class Phrase
  
  def initialize(message)
  	@message = message  
  end	

  def word_count  	
    tokenize().each_with_object(Hash.new(0)) do |word, hash|       
      hash[word] += 1
    end    
  end

  private    
    def tokenize
      @message.downcase.scan(/\w+/)
    end	
end
