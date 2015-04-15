class Difference
  attr_reader :initial_character, :second_character

  def initialize(initial_character, second_character)
    @initial_character = initial_character
    @second_character = second_character
  end

  def amount
    if initial_character == second_character
      0
    else
      1
    end
  end
end


class Hamming
  def self.compute(first, second)
    total_difference = 0
    first = make_first_dna_strand_no_longer_than_second(first, second)
    string_to_array(first).each_with_index do |key, value|
      total_difference += Difference.new(key, string_to_array(second)[value]).amount
    end
    total_difference
  end

  private

  def self.make_first_dna_strand_no_longer_than_second(first, second)
    return first[0, second.length]
  end

  def self.string_to_array(string)
    return string.split(//)
  end
end
