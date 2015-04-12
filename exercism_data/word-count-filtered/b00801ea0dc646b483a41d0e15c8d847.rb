class Phrase
  attr_accessor :word

  def initialize(word)
  	@word = word.downcase.scan(/[\w']+/)
  end

  def word_count
  	hash = {}
  	@word.each {|x| hash[x] = @word.count(x)}
  	hash
  end

end
