class Anagram
  def initialize(word)
    @word           = word.downcase
    @word_point_map = build_codepoint_count_map(@word)
  end

  def match(candidates)
    candidates.select do |candidate|
      matches_length?(candidate)         &&
      not_identical?(candidate.downcase) &&
      equivalent_codepoints?(candidate.downcase)
    end
  end

  private
  def matches_length?(candidate)
    @word.length == candidate.length
  end

  def not_identical?(candidate)
    @word != candidate
  end

  def equivalent_codepoints?(candidate)
    point_map = build_codepoint_count_map(candidate)

    @word_point_map == point_map
  end

  def build_codepoint_count_map(word)
    word.codepoints.each_with_object(Hash.new(0)) do |codepoint, map|
      map[codepoint] += 1
    end
  end
end
