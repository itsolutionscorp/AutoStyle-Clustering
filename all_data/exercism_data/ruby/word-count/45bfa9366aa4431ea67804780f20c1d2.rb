class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    count Parse.new(@phrase).words
  end
   
  private
  def count words
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1}
  end
end

class Parse
  def initialize words
    @words = words
  end
  
  def words
    @words.downcase.scan(/\w+/)
  end

end
