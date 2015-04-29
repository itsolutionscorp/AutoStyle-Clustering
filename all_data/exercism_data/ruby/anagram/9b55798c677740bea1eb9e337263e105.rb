class Anagram

  def initialize word
    @word = word
    @normalized_word = normalize_word word
  end

  def match list
    list.select {|try| matches? try }
  end

  def matches? try
    return false if same_word? try

    @normalized_word == normalize_word(try)
  end

  private

  def same_word? try
    @word.downcase == try.downcase
  end

  def normalize_word word
    word.downcase.chars.sort
  end
end
