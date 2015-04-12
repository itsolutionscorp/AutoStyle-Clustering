class Phrase
  
  WORDS_WITH_APOSTROPHES = /[^\w']+/
  
  def initialize(phrase)
    @split = create_word_array(phrase)
  end
  
  def word_count
    count = Hash.new(0)
    @split.each do |word|
      count[word.downcase] += 1
    end
    
    count
  end
  
  def create_word_array(input_string)
    input_string.split(WORDS_WITH_APOSTROPHES).reject(&:empty?)
  end
  
end
