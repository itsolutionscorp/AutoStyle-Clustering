class Anagram
  attr_reader :input

  def initialize input
    @input = input.downcase
  end

  def match potentials
    potentials.each.with_object([]) do |potential, matches|
      matches << potential if anagram? potential
    end
  end

  private

  def anagram? potential
    hashed_word == hashify(potential.downcase) && input != potential.downcase
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
