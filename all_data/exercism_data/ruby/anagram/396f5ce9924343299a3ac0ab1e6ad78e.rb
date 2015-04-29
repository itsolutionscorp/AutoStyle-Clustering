class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(potential_matches)
    matches = []
    potential_matches.each do |potential|
      if potential.length == @word.length
        if (@word.chars.sort.join == potential.downcase.chars.sort.join) && (@word != potential.downcase)
          matches << potential
        end
      end
    end

    return matches
  end
end
