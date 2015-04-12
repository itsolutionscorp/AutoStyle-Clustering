class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end
  
  def word_count
    @word_count ||= count_words
  end
  
  private
  
  def count_words
    phrase_words.each_with_object(Hash.new 0) do |word, words| 
      words[word] += 1
    end
  end
  
  def phrase_words
    @phrase_words ||= @phrase.downcase.scan(/\w+/)
  end
end
