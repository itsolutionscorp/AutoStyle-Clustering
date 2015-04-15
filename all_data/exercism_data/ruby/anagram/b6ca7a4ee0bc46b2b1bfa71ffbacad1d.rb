class Anagram
  attr_reader :word

  def initialize word
    @word = word.downcase
  end

  def match candidates
    candidates.select do |candidate|
      matches? Anagram.new candidate
    end
  end

  def matches? other
    letters == other.letters and
       word != other.word
  end

  def letters
    @letters ||= word.chars.sort
  end
end
