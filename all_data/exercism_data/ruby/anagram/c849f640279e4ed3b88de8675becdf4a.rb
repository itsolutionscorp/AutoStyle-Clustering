class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    letters = prep(@word)
    anagrams = Array.new
    words.each do |eval_word|
      eval_letters = prep(eval_word)
      break if eval_letters.eql?(letters)
      anagrams << eval_word if eval_letters.sort.eql?(letters.sort)
    end
    anagrams
  end

  def prep(word)
    word.downcase.split(//)
  end

end
