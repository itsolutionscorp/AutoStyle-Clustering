class Anagram
  def initialize(word)
    @word = word
    @occurrences = occurrences(word)
  end

  def match(words)
    words.select do |word|
      !word.casecmp(@word).zero? && occurrences(word) == @occurrences
    end
  end

  private

    def occurrences(word)
      word.downcase.chars.reduce(Hash.new(0)) do |occurrences, char|
        occurrences[char] += 1
        occurrences
      end
    end
end
