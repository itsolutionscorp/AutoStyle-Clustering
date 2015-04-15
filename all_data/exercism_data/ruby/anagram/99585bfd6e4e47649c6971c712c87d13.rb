class Anagram
  def initialize(word)
    @word_to_test = word
  end

  def match(words_arr)
    words_arr.select do |word|
    	if word.downcase != @word_to_test.downcase
    		modify_to_test(word) == modify_to_test(@word_to_test)
    	end
    end
  end

  def modify_to_test(word)
  	word.downcase.split("").sort.join("")
  end
end
