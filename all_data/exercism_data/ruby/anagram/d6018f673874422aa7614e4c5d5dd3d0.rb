class Anagram

  def initialize(word)
  	@word = word
  end
  
  def match(wordlist)
  	target = sort(normalize(@word))
  	wordlist.select do |item|
  	  sorted_item = sort(normalize(item))
  	  sorted_item == target unless normalize(item) == normalize(@word)
  	end
  end

  private

  def normalize(str)
  	str.to_s.downcase
  end

  def sort(str)
  	str.chars.sort
  end

end
