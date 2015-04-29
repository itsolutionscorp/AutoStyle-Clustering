class Anagram
  def initialize(subject)
    @subject = subject
  end

  def match(possibilities)
    possibilities.select do |word|
      same_letters?(word, @subject) && different_word?(word, @subject)
    end
  end

  private

  def same_letters?(a, b)
    sort(a) == sort(b)
  end

  def different_word?(a, b)
    a.casecmp(b) != 0
  end

  def sort(word)
    word.downcase.chars.sort.join
  end
end
