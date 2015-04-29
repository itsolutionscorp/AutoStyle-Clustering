class Phrase
  attr_reader :words, :phrase

  def initialize(phrase)
    @phrase = phrase 
    phrase_to_words
  end

  def word_count
    unique_words.each_with_object({}) do |word, count_hash|
      count_hash[word] = words.count(word)  
    end
  end
  
  def clean_phrase
    phrase.downcase.gsub(/[^0-9a-zA-Z, ]/i, '').gsub(',', ' ')
  end

  def phrase_to_words 
    @words = clean_phrase.split(" ")
  end

  def unique_words
    words.uniq
  end
end
