class Anagram
  def initialize(word)
    @value = Match.new(word)
  end

  def match(words)
    words.each_with_object(Array.new()) do |word, matches|
      if word.downcase != @value.word && word.downcase.split('').sort == @value.chars
        matches << word
      end
    end
  end
end

class Match
  attr_reader :word, :chars

  def initialize(word)
    @word = word.downcase
    @chars = @word.split('').sort
  end
end
