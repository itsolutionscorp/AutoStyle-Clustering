class Anagram
  def initialize(word)
    @word = word
    @letters_count = count_letters(word)
  end

  def match(test_words)
    test_words.select { |w| anagram?(w) }
  end

  private
  def anagram?(w)
    return false if w.downcase == @word.downcase
    count_letters(w) == @letters_count
  end

  def count_letters(w)
    counts = Hash.new(0)
    w.downcase.each_char do |char|
      counts[char] += 1
    end
    counts
  end

end
