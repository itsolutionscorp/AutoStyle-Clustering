class Anagram

  def initialize(source)
    @source = source.downcase
  end

  def match(matches)
    matches.map!(&:downcase).uniq!
    matches.select {|match| @source.chars.sort.join.start_with?(match.chars.sort.join) && match != @source }
  end

end
