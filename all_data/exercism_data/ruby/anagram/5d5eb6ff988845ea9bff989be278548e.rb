class Anagram
  class Word
    attr_reader :chars, :counts

    def initialize(chars)
      @chars = chars.downcase
      @counts = count_chars
    end

    def match?(other)
      chars != other.chars && counts == other.counts
    end

    private

    def count_chars
      chars.each_char.each_with_object(Hash.new(0)) do |char, counts|
        counts[char] += 1
      end
    end
  end

  attr_reader :word
  
  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates)
    candidates.select{|c| anagram?(c)}
  end

  def anagram?(candidate)
    word.match? Word.new(candidate)
  end
end
