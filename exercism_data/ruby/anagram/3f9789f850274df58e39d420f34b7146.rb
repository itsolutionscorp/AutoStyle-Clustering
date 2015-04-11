class Anagram
  def initialize input
    @input = input
  end

  def match potentials
    potentials.select{ |potential| anagram? potential }
  end

  private

  def anagram? potential
    potential = potential.downcase
    comparable_input == convert(potential) && input.downcase != potential
  end

  def comparable_input
    @comparable_input ||= convert input.downcase
  end

  def convert word
    word.split("").sort
  end

  attr_reader :input
end
