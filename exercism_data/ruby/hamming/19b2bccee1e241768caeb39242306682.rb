class DNA
  attr_accessor :sequence

  def initialize(sequence)
    self.sequence = sequence
  end

  def hamming_distance(other_sequence)
    differences_bitmap(other_sequence).compact.inject(&:+) || 0
  end

  private

  def differences_bitmap(other_sequence)
    sequence.chars.zip(other_sequence.chars).map {|nucleotide_pair| compare_nucleotide_pair(nucleotide_pair) }
  end

  # We could make the length mismatch difference 0 and avoid the call to
  # compact on line 9, but then #differences_bitmap is not really identifying
  # differences.
  def compare_nucleotide_pair(nucleotide_pair)
    if nucleotide_pair.include? nil
      nil
    elsif nucleotide_pair.first == nucleotide_pair.last
      0
    else
      1
    end
  end
end
