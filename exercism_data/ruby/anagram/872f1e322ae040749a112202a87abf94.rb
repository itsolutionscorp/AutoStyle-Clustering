class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(possible_matches)

    possible_matches.select do |candidate|
      word.anagram_of?(candidate)
    end

  end

end


class Word

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def anagram_of?(word)
    break_down(text) == break_down(word)
  end

  def break_down(string)
    string.downcase.chars.to_a.sort
  end

end
