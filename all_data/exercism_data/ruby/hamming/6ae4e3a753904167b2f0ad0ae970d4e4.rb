class Hamming

  def self.compute(sequence_1st, sequence_2nd)
    self.new(sequence_1st, sequence_2nd).compute
  end

  def initialize(sequence_1st, sequence_2nd)
    @sequence_1st = sequence_1st.chars
    @sequence_2nd = sequence_2nd.chars
  end

  def compute
    positions.count do |pair|
      base_pair_coupling?(pair)
    end
  end

  private

  attr_reader :sequence_1st, :sequence_2nd

  def base_pair_coupling?(i)
    sequence_1st[i] != sequence_2nd[i]
  end

  def positions
    sequence_1st.size.times
  end

end
