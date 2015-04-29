class Anagram

  def initialize(subject)
    @subject = subject
  end

  def match(words)
    words.select do |word|
      letters(@subject)   == letters(word)   &&
      normalize(@subject) != normalize(word)
    end
  end

  private
  def normalize(word)
    word.downcase
  end

  def letters(word)
    word.downcase.chars.sort.join
  end

end
