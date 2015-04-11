class Phrase
  
  def initialize string
    @string = string
  end
  
  def word_count
    extract_words_from(@string.downcase).each_with_object(Hash.new(0)) do |word, hash| 
      hash[word] += 1
    end
  end
  
  private
  
  def extract_words_from phrase
    phrase.downcase.split(/[^\w]+/)
  end
  
end
