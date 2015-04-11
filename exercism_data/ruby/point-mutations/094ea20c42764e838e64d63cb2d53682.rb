class DNA
  BASES = %w{A T C G}

  def initialize(base_chain)
    @chain = base_chain.chars
  end

  def hamming_distance(target)
    distance = 0
    @chain.zip(target.chars).each do |b1,b2|
      if b1 && b2
        distance += 1 if b1 != b2
      end
    end
    distance
  end
end
