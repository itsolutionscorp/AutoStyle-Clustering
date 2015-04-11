class Anagram
  
  def initialize(word)
    @word = word.to_s
  end

  def match(words)
    words = filter_lengths(words)
    matches = find_matches(words)
    clean_matches(matches)
  end

  private
  def filter_lengths(words)
    words.select {|word| @word.length == word.length}
  end

  def find_matches(words)
    words.select {|word| jumbled_match?(word)}
  end

  def jumbled_match?(word)
    jumble(word).include?(@word.downcase)
  end

  def jumble(word)
    word.downcase.chars.permutation.map {|w| w.join}
  end

  def clean_matches(matches)
    matches.reject {|word| word.downcase == @word.downcase}
  end

end
