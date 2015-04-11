class String
  def case_insensitive_eql?(arg)
    self.casecmp(arg) == 0
  end

  def alphabetized
    self.downcase.chars.sort.join
  end
end

class Anagram
  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |word|
      unless word.case_insensitive_eql? @word
        word.alphabetized == @word.alphabetized
      end
    end
  end
end
