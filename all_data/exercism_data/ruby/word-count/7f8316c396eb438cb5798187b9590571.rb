class Phrase
  attr_accessor :phrase, :word_count

  def initialize(input)
    @phrase = input.downcase.gsub(/[^0-9a-z' ]/i, ' ').split()
    @word_count = counter(input)
  end

  def counter(input)
    phrase.each_with_object(Hash.new(0)) {|word, hsh| hsh[word] += 1} 
  end
  
end
