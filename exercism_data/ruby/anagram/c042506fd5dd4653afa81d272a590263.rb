class Anagram
  attr_reader :hashed_pivot
  private :hashed_pivot

  def initialize(word)
    @hashed_pivot = hash_word(word)
  end

  def match(candidates)
    candidates.select { |c| hash_word(c) == hashed_pivot }
  end

  private
  def hash_word(word)
    word.downcase.chars.sort.hash
  end
end
