class Anagram
  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    lower_word = word.downcase
    candidates.each_with_object([]) do |candidate,results|
      lower_candidate = candidate.downcase
      if index(lower_candidate) == index(lower_word) && lower_candidate != lower_word
        results << candidate
      end
    end
  end

  def index(string)
    string.chars.sort
  end
end
