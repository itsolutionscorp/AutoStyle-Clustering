class Anagram
  def initialize(match_word)
    @match_word = match_word.downcase
  end

  def match(words)
    words_array = []
    words.each do |word|
      words_array << word if cypher(word)
    end
    words_array
  end

  def cypher(word)
    word = word.downcase
    if word.size != @match_word.size
      false
    elsif word == @match_word
      false
    else
      letters = word.split("")
      match_letters = @match_word.split("")
      if match_letters.sort == letters.sort
        true
      else
        false
      end
    end
  end

end
