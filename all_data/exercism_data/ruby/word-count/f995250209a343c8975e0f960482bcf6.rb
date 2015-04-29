class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    Parse.new(@phrase).count
  end
end

class Parse
  def initialize words
    @words = words.downcase.scan(/\w+/)
  end
  
  def count
    @words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1}
  end

end
