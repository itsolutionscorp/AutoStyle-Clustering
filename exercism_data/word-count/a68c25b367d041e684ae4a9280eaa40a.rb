class Phrase
  def initialize(input)
    @input = input.downcase
  end
  
  def word_count
    words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end
  
  private
  
  def words
    @input.scan(/[\w']+/)
  end
end
