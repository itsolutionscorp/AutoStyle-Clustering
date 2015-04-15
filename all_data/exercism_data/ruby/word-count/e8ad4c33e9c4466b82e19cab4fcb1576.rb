class Phrase
  def initialize(sentence)
    @sentence = sentence
  end
  def word_count()
  
    @h1 = Hash.new
    split_to_lower_alphanumeric_words.each {|x| update_or_insert(x) }
    
    return @h1;
  end
  
  private 
  def split_to_lower_alphanumeric_words
    return @sentence.downcase().scan(/\w+/)
  end
  
  private
  def update_or_insert(word)
     if(@h1.has_key?(word))
        @h1[word] += 1
      else
        @h1[word] = 1
      end
  end
end
