class Anagram
  attr_reader :input

  def initialize input
    @input = input.downcase
  end

  def match potentials
    matches = []
    potentials.each do |potential|
      if anagram? potential.downcase
        matches << potential
      end
    end
    matches
  end

  private

  def anagram? potential
    hashed_word == hashify(potential) && input != potential
  end

  def hashed_word
    @hashed_word ||= hashify input
  end

  def hashify word
    word.split("").each.with_object(Hash.new 0) do |character, count|
      count[character] += 1
    end
  end
end
