class Anagram

  def initialize(source)
    @source = source.downcase
  end

  def match(matches)
    matches = matches.map(&:downcase).uniq.reject { |match| match == @source }
    matches.select { |match| contains?(match) }
  end

  private

  def contains?(word)
    @source.chars.sort.join.start_with?(word.chars.sort.join)
  end

end
