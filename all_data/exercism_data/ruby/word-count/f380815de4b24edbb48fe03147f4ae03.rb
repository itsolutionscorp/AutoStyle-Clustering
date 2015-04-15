class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end
  
  def word_count
    unless @words
      @words = Hash.new 0
      @phrase.scan(/\w+/) { |word| @words[word.downcase] += 1 }
    end
    @words
  end
end
