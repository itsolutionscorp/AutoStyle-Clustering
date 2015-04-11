class Anagram
  def initialize word
    @word = word
    @chars = sort_word word
  end

  def match words
    words.select { |w| is_anagram? w }
  end

  private

  def sort_word word
    word.chars.map(&:downcase).sort
  end

  def is_anagram? word
    sort_word(word) == @chars && !@word.match(/#{word}/i)
  end
end
