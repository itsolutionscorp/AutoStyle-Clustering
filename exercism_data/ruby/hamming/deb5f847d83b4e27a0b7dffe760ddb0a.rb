class Hamming

  def self.compute(reference, partner)
    self.new(reference, partner).compute
  end



  def initialize(reference, partner)
    @reference = reference.chars
    @partner = partner.chars
  end

  def compute
    overlap.count do | pair|
        base_pair_matching?(pair)
    end
  end

  private

  attr_reader :reference, :partner

  def base_pair_matching?(pair)
    pair.first != pair.last
  end

  def overlap
    reference.zip(partner)
  end

end
