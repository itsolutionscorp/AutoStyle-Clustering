class Phrase < String
  
  def initialize(str)
    super(str.downcase)
  end  

  def word_count
    words = get_words
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
  
  private
    
    def get_words
      downcase.scan(/\w+/)
    end
end
