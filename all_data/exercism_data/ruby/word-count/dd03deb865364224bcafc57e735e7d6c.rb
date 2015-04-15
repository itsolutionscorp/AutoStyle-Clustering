class Phrase
  attr_reader :word_count
  
  def initialize(phrase)
    @word_count = count_words(phrase)
  end
  
  def count_words(phrase)
    words = phrase.downcase.split(/[^\w']+/)
    result = Hash.new
    words.each do |word|
      if result.has_key?(word)
        result[word] = result[word] + 1
      else
        result[word] = 1
      end
    end
    result
  end
end
