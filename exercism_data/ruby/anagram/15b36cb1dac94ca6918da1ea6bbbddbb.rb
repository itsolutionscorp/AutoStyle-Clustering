class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    anagrams = Array.new
    letters = prepped_array(@word)
    words.each do |eval|
      eval_letters = prepped_array(eval)
      break if eval_letters[0].eql?(letters[0])
      anagrams << eval if eval_letters[1].eql?(letters[1])
    end
    anagrams
  end

  def prepped_array(word)
    [word.downcase.split(//), word.downcase.split(//).sort]
  end

end
