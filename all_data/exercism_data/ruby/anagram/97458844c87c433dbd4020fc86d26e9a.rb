module Process

  def self.alphabetize(word)
    word.downcase.chars.sort
  end
end

class Anagram

  def initialize(word)
    @letters = Process.alphabetize(word)
  end

  def match(word_list)
    word_list.select {|word| Process.alphabetize(word) == @letters}
  end

end
