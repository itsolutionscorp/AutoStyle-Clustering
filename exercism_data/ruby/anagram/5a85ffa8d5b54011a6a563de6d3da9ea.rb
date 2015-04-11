class Anagram
  def initialize(root)
    @root = root
  end

  def match(possibilities = [])
    possibilities.delete_if do |word|
      different_letters?(word, root) || same_word?(word, root)
    end
  end

  private

  attr_reader :root

  def different_letters?(a, b)
    sort(a) != sort(b)
  end

  def same_word?(a, b)
    a.casecmp(b).zero?
  end

  def sort(word)
    word.downcase.chars.sort.join
  end
end
