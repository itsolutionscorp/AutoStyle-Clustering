class Phrase
  def initialize(sentence)
    @words = sentence.scan(/\w+/)
  end
  
  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end
end
