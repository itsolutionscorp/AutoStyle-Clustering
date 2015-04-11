class Hamming
  def self.compute(sequence, other)
    hamming          = Hamming.new(sequence, other)
    shared_sequences = hamming.shared
    hamming.differences(shared_sequences).size
  end

  def initialize(sequence, other)
    @sequence = sequence.split("")
    @other    = other.split("")
  end

  def differences(shared_sequences)
    shared_sequences.select { |x, y| x != y }
  end

  def shared
    if @sequence.size < @other.size
      @sequence.zip(@other)
    else
      @other.zip(@sequence)
    end
  end
end
