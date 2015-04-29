class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    find_matches(words).delete_if { |word| bad_match?(word) }
  end

  private

  def find_matches(words)
    # http://rubular.com/r/so5YtfRyQu and http://rubular.com/r/iC18DJlUV9
    length_match = "{#{@word.length}}"
    words.map { |w| w.match(/\b[#{@word}]#{length_match}\b/i).to_s }
  end

  def bad_match?(match_word)
    match_word.empty? || match_word.downcase == @word.downcase || !LetterCount.match?(@word, match_word)
  end
end

class LetterCount
  def initialize(word)
    @word = word.downcase
  end

  def count_letters
    @word.split(//).each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  def self.match?(word1, word2)
    counts1 = LetterCount.new(word1).count_letters
    counts2 = LetterCount.new(word2).count_letters
    counts1.sort == counts2.sort
  end
end
