class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    anagrams = Array.new
    letters = prepped_array(@word)
    words.each do |eval|
      eval_letters = prepped_array(eval)
      break if eval_letters[:unsorted].eql?(letters[:unsorted])
      anagrams << eval if eval_letters[:sorted].eql?(letters[:sorted])
    end
    anagrams
  end

  def prepped_array(word)
    {:unsorted => word.downcase.split(//), :sorted => word.downcase.split(//).sort}
  end

end
