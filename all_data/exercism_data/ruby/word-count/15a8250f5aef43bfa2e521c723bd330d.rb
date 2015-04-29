class Phrase
  def initialize(str)
    @sentence = str.downcase
  end
  
  def word_count
    @sentence.scan(/[\w']+/).inject(Hash.new(0)){|hash, word| hash[word] += 1; hash}
  end

end
