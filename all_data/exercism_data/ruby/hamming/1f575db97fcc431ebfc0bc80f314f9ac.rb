class Hamming
  attr_reader :strand1, :strand2
  attr_accessor :distance

  def initialize strand1, strand2
    @strand1 = strand1
    @strand2 = strand2

    self.distance = count_distance
  end

  def self.compute strand1, strand2
    self
      .new(strand1, strand2)
      .distance
  end

private
  def character_matches? index
    strand1[index] == strand2[index]
  end

  def num_chars_to_check
    [strand1.length, strand2.length].min
  end

  def count_distance
    num_chars_to_check.times.count do |index|
      !character_matches? index
    end
  end
end
