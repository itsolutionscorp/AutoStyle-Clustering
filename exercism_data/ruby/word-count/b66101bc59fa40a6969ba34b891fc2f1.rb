class Phrase
  def initialize(input)
    @input = input
  end
  
  def word_count
    words.each_with_object(counter) { |w, c| c[key_for(w)] += 1 }
  end
  
  private
  
  def counter
    Hash.new {|h,k| h.default = 0}
  end
  
  def key_for(word)
    word.to_s.strip.downcase
  end
  
  def words
    @input.scan(/[a-zA-Z0-9]+/)
  end
end
