class Phrase < String
  
  def initialize(str)
    super(str.downcase)
  end  

  def word_count
    get_words.each_with_object(Hash.new(0)) { |word, word_occurrences| word_occurrences[word] += 1 }
  end
  
  private
    
    def get_words
      scan(/\w+/)
    end
end
