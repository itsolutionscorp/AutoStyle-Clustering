class Anagram
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
    hashed_word == convert(potential.downcase) && input != potential.downcase
  end

  def hashed_word
    @hashed_word ||= convert input
  end

  def convert word
    word.split("").sort
  end

  attr_reader :input
end
