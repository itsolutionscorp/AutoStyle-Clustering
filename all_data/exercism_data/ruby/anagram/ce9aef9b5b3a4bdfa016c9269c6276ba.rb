class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    matches = []
    list.each do |word|
      next if letters_mismatch?(word)
      next if same_word?(word)
      matches << word if letters_match?(word)
    end

    matches
  end

  private

  def length
    @word.length
  end

  def chars
    @word.downcase.split(//).sort
  end

  def letters_mismatch?(word)
    word.length != length
  end

  def same_word?(word)
    @word.downcase == word.downcase
  end

  def letters_match?(word)
    word.downcase.split(//).sort == chars
  end
end
