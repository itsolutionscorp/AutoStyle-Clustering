class Phrase
  def initialize(words)
    @words = words
  end
  
  def word_count
    @words.downcase.scan(/\w+/).inject(Hash.new(0)) { |list,word| list.merge!({ word => list[word]+1 }) }
  end
end
