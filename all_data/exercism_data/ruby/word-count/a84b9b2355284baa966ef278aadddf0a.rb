class Phrase
  attr_reader :word_count
  
  def initialize phrase
    @phrase = parse(phrase)
    @word_count = count_words
  end

  private

  def count_words
    words = Hash.new(0)
    
    @phrase.each do |word| 
      words[word.downcase] += 1
    end

    return words
  end

  def parse phrase
    phrase.scan(/[\w\']+/)
  end
end
