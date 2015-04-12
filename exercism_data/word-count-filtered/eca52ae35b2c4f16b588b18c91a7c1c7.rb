class Phrase
  def initialize(text)
    @result = Hash.new(0)
    text.scan(/[0-9a-zA-Z']+/).each { |word| 
      @result[word.downcase] += 1
    }
  end

  def word_count
    @result
  end
end
