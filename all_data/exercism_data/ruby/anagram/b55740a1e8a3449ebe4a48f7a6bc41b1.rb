class Anagram
  def initialize(source)
    @source = source
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    @source.downcase != word.downcase &&
      chars_count(@source) == chars_count(word)
  end

  def chars_count(word)
    map = word.downcase
              .chars
              .group_by { |char| char }
              .map      { |char, ocurrences| [char, ocurrences.size] }
    Hash[map]
  end
end
