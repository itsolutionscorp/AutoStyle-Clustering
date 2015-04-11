class Anagram
  def initialize(match_word)
    @match_word = match_word
  end

  def match(words)
    words_array = []
    words.each do |word|
      words_array << word if cypher(word)
    end
    words_array
  end

  def cypher(word)
    if word.size != @match_word.size
      false
    elsif word.downcase == @match_word.downcase
      false
    else
      letters = word.downcase.split("")
      match_letters = @match_word.downcase.split("")
      if match_letters.sort == letters.sort
        true
      else
        false
      end
    end
  end

end
