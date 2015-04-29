class Hamming

  def self.compute(sequence_1st, sequence_2nd)
    self.new(sequence_1st, sequence_2nd).compute
  end

  def initialize(sequence_1st, sequence_2nd)
    @sequence_1st = sequence_1st.chars
    @sequence_2nd = sequence_2nd.chars
  end

  def compute
    overlap.count do |pair|
        base_pair_matching?(pair)
    end
  end

  private

  attr_reader :sequence_1st, :sequence_2nd

  def base_pair_matching?(pair)
    pair.first != pair.last
  end

  def overlap
    sequence_1st.zip(sequence_2nd)
  end

end
