class Anagram
  def initialize(word)
    @word          = word.downcase
    @word_char_map = build_character_count_map(@word)
  end

  def match(candidates)
    candidates.select do |candidate|
      matches_length?(candidate)         &&
      not_identical?(candidate.downcase) &&
      equivalent_characters(candidate.downcase)
    end
  end

  private
  def matches_length?(candidate)
    @word.length == candidate.length
  end

  def not_identical?(candidate)
    @word != candidate
  end

  def equivalent_characters(candidate)
    char_map = build_character_count_map(candidate)

    @word_char_map == char_map
  end

  def build_character_count_map(word)
    word.chars.each_with_object(Hash.new(0)) do |char, map|
      map[char] += 1
    end
  end
end
