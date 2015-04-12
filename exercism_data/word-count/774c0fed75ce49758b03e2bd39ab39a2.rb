class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    result = Hash.new(0)
    individual_words.each { |word| result[word] += 1}
    result
  end
  
  def individual_words
    @string.downcase.gsub(/[^\w\s\d\']/, ' ').split
  end
  
  

end
