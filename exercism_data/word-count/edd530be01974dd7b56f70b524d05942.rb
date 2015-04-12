class Phrase
  attr_reader :word_count

  def initialize( phrase )
    @phrase = phrase
    @word_count = count_words
  end

  
  private 
  def normalize_word(word)
    word.downcase.gsub(/[^\w']/, '')
  end

  def count_words
    word_count = {}
    @phrase.scan(/[\w']+/).each do | word |
      word = normalize_word word
      word_count[word] = 1 + (word_count[word] || 0 ) 
    end

    word_count
  end


end
