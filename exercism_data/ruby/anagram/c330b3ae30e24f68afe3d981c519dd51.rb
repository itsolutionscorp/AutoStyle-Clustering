class Anagram
  attr_accessor :word

  def initialize(word)
  	@word = word.downcase
  end

  def check_for_dups(arg)
  	arg.each {|r| arg.delete(r) if word == r.downcase}
  end

  def match(arr)
  	check_for_dups(arr)
  	arr.select {|i| i.downcase.sorted == word.sorted }
  end
end

class String
  def sorted
  	self.split("").sort.join
  end
end
