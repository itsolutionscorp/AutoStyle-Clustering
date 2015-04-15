class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    anagrams = Array.new
    letters = prepped(@word)
    words.each do |eval|
      eval_letters = prepped(eval)
      break if eval_letters.eql?(letters)
      anagrams << eval if eval_letters.sort.eql?(letters.sort)
    end
    anagrams
  end

  def prepped(word)
    word.downcase.split(//)
  end

end
