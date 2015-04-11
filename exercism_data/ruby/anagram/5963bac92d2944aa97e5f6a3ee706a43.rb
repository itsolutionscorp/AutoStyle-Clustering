class Anagram
  def initialize(anagram)
    @original = anagram
  end

  def match(words)
    words.select do |word|
      different_word?(word) && same_letters?(word)
    end
  end

  private

  def letterset(string)
    string.downcase.chars.sort
  end

  def chars
    @letters ||= letterset @original
  end

  def same_letters?(word)
    chars == letterset word
  end

  def different_word?(word)
    word.downcase != @original.downcase
  end
end
