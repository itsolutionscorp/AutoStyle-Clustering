class Phrase

  WORD_REGEXP = /[\w']+/

  def initialize(string)
    @string = string
  end

  def word_count
    word_count = Hash.new
    word_array = @string.downcase.scan(WORD_REGEXP)
    
    word_array.each do |word, count|
      count = word_array.count(word)
      word_count[word] = count
    end
    word_count
  end

end
