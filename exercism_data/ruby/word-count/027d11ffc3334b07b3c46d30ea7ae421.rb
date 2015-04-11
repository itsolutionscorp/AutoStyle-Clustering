class Phrase
  
  def initialize (phrase)
    @phrase = phrase
  end

  def word_count
    words_to_count = create_clean_words_array_from @phrase
    counted_words={}
    words_to_count.uniq.each do |word|
      counted_words[word] = words_to_count.count(word)
    end
    counted_words
  end
 
  private 

  def remove_clutter phrase
    phrase.downcase.gsub(/\W+/,' ')
  end

  def create_clean_words_array_from string
    clean_string= remove_clutter string
    words_array = clean_string.split
  end

end
