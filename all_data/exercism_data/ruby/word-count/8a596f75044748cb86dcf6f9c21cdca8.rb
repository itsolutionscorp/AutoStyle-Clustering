class Phrase
  
  def initialize phrase
    @string = phrase.to_s
  end
  
  def word_count
    extract_words_from(@string.downcase).each_with_object(Hash.new(0)) do |word, hash| 
      hash[word] += 1
    end
  end
  
  private
  
  def extract_words_from string
    string.split(/[^\w]+/)
  end
  
end
