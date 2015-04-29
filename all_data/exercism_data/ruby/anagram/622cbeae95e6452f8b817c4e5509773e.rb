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
    haystack, needle = *[@source, word].map{ |w| w.chars.sort.join }
    haystack.start_with?(needle)
  end

end
