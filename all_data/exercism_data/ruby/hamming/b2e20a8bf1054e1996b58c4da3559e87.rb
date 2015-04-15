class Hamming
  def self.compute(sequence1, sequence2)
    hamming          = Hamming.new(sequence1, sequence2)
    shared_sequences = hamming.shared
    hamming.differences(shared_sequences)
  end

  def initialize(sequence1, sequence2)
    @sequence1 = sequence1.chars
    @sequence2 = sequence2.chars
  end

  def differences(shared_sequences)
    shared_sequences.count { |x, y| x != y }
  end

  def shared
    @sequence1.zip(@sequence2).first(@sequence2.length)
  end
end
