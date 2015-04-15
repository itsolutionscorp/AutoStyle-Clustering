class Anagram
  def initialize base_word
    base_word.downcase!
    @letters_count = letter_count base_word
    @base_word = base_word
  end

  def match possibilities
    possibilities.dup.keep_if {|word| letter_count(word.downcase) == @letters_count && word.downcase != @base_word}
  end

  private

  def letter_count word
    counts = Hash.new do |hash, letter|
      hash[letter] = 0
    end
    word.each_char do |letter|
      counts[letter] += 1
    end
    counts
  end
end
