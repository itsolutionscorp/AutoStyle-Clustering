class Anagram

  def initialize(subject)
    @subject = subject.downcase
  end

  def match(words)
    words.map!(&:downcase).uniq!
    words.select {|word| letters(@subject) == letters(word) && word != @subject }
  end

  private
  def letters(word)
    word.chars.sort.join
  end

end
