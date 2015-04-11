class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |word|
      word.canonical == @word.canonical && word.downcase != @word.downcase
    end
  end
end

class String
  def canonical
    self.downcase.split(//).sort.join
  end
end
