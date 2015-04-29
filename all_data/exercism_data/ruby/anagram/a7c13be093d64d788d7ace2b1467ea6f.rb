class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(potential_matches)
    matches = []
    potential_matches.each do |potential|
      if potential.length == @word.length
        if (@word.chars.sort.join == potential.downcase.chars.sort.join) && @word.is_not_the_same(potential)
          matches << potential
        end
      end
    end

    return matches
  end

  private

  def is_not_the_same(potential)
    @word != potential.downcase
  end
end
