class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    count_of_words = Hash.new
    words = get_words(@phrase)
    words.each do |word|
      if count_of_words.key?(word)
        count_of_words[word] = count_of_words[word] + 1
      else
        count_of_words[word] = 1 
      end
    end
    count_of_words
  end

  def get_words(phrase)
    phrase.downcase.split(/[^\w+']/).delete_if(&:empty?)
  end

end
