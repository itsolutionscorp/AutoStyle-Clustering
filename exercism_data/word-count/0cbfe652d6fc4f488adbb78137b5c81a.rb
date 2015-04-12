class Phrase
  def initialize(sentence)
    @sentence = sentence
  end
  def word_count()
    @h1 = Hash.new
    
    for s in split_to_lower_alphanumeric_words(@sentence)do
      update_or_insert(s)
    end
    
    return @h1;
  end
  
  def split_to_lower_alphanumeric_words(sentence)
    return sentence.downcase().gsub(/[^0-9a-zA-Z]/, ' ').split()
  end
  
  def update_or_insert(word)
     if(@h1.has_key?(word))
        @h1[word] += 1
      else
        @h1[word] = 1
      end
  end
end