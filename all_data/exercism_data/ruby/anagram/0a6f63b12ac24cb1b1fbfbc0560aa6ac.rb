class Anagram
  def initialize word
    @word = word
  end

  def match potential
    potential.select do |match|
      (@word.downcase != match.downcase) && sorted(@word) == sorted(match)
    end
  end

  def sorted str
    str .downcase.chars.sort.join
  end
end
