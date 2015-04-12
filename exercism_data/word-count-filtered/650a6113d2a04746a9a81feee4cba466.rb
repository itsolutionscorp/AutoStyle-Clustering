class Phrase
  def initialize(sentence)
    @words = sentence.split(/\W+/).map {|word| word.downcase }
  end
  
  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
