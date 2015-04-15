class Hamming

  def self.compute(reference, partner)
    self.new(reference, partner).compute
  end

  attr_reader :reference, :partner

  def initialize(reference, partner)
    @reference = reference.split('')
    @partner = partner.split('')
  end

  def compute
    overlap.inject(0) do |sum, pair|
        sum += 1 if base_pair_matching?(pair); sum
    end
  end

  private

  def base_pair_matching?(pair)
    pair.first != pair.last
  end

  def overlap
    reference.zip(partner)
  end

end
