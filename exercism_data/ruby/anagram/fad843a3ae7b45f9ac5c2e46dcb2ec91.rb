class Anagram

  attr_reader :given_word

  def initialize(word)
    @given_word = word
  end

  def normalized(word)
    word.downcase.chars.sort 
  end

  def match(list)
    unique_list(list).select { |w| normalized(w) == normalized(given_word)}
  end
  
  def unique_list(list)
    list.select { |w| w.downcase != given_word.downcase}
  end

end
