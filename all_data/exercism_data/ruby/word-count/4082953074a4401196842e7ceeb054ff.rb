class Phrase
  def initialize(input)
    @input = input
  end
  
  def word_count
    words.each_with_object(Hash.new(0)) do |w, c| 
      c[key_for(w)] += 1 
    end
  end
  
  private
  
  def key_for(word)
    word.to_s.strip.downcase
  end
  
  def words
    @input.scan(/\w+/)
  end
end
