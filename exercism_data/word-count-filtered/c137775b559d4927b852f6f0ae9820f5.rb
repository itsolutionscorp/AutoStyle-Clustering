class Phrase
  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def word_count
    hash = Hash.new(0)
    words = []
    word.gsub(/[,:]/, " ").split(" ").collect{|x| words << x.match(/^[a-zA-Z0-9']*/)}
    words.each do |key|
      hash[key.to_s.downcase] += 1 
    end
    hash
  end
end
