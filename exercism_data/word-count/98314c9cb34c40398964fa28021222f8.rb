class Phrase
  def initialize phrase
    @phrase = phrase.to_s
    @words = Hash.new 0
  end
  
  def word_count
    count_words if @words.empty?
    @words
  end
  
  private
  
  def count_words
    phrase_words.each do |word| 
      increment_word_count word
    end
  end
  
  def increment_word_count word
    @words[word] += 1
  end
  
  def phrase_words
    phrase_words ||= @phrase.downcase.scan(/\w+/)
  end
end
