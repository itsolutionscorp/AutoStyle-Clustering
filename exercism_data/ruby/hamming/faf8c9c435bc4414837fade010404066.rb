class Hamming
  def self.compute(sequence, other)
    hamming          = Hamming.new(sequence, other)
    shared_sequences = hamming.shared
    hamming.differences(shared_sequences)
  end

  def initialize(sequence, other)
    @sequence = sequence.chars
    @other    = other.chars
  end

  def differences(shared_sequences)
    shared_sequences.count { |x, y| x != y }
  end

  def shared
    if @sequence.size < @other.size
      @sequence.zip(@other)
    else
      @other.zip(@sequence)
    end
  end
end
