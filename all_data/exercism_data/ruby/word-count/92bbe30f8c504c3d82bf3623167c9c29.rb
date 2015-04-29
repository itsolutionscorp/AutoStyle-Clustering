class Phrase
  attr_reader :words, :input

  def initialize(input)
    @input = input
  end

  def word_count
    unique_words.each_with_object({}) do |word, frequencies|
      frequencies[word] = words.count(word)  
    end
  end
  
  def formatted_input 
    input.downcase.gsub(/[^0-9a-zA-Z, ]/i, '').gsub(',', ' ')
  end

  def words 
     formatted_input.split(" ")
  end

  def unique_words
    words.uniq
  end
end
