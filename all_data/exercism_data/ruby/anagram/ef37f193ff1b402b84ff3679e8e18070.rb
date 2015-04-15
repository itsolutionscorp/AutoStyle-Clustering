class Anagram
  def initialize(letters)
    @sequence = Sequence.new(letters)
  end

  def match(words)
    words.select do|word|
      candidate = Sequence.new(word)
      @sequence.anagram_with?(candidate)
    end
  end
end

class Sequence
  def initialize(letters)
    @letters = letters.downcase.chars
  end

  def anagram_with?(candidate)
    char_count.eql? candidate.char_count
  end

  protected

  def char_count
    Hash[@letters.uniq.map { |ch| [ch, @letters.count(ch)] }]
  end
end
