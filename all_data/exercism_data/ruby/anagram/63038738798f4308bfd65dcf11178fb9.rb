class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(potential_matches)
    matches = []
    potential_matches.each do |potential|
      if (have_same_characters(potential)) && is_not_the_same_as(potential)
        matches << potential
      end
    end
    matches
  end

  private
  def is_not_the_same_as(potential)
    @word != potential.downcase
  end

  def have_same_characters(potential)
    @word.chars.sort.join == potential.downcase.chars.sort.join
  end

end
