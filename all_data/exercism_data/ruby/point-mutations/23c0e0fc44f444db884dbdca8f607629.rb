class DNA
  def initialize(base_chain)
    @chain = base_chain.chars
  end

  def hamming_distance(target)
    pairs_with(target).count { |pair| mismatch?(*pair) }
  end

  private

  def pairs_with(target)
    @chain.zip(target.chars)
  end

  def mismatch?(b1, b2)
    b1 && b2 && (b1 != b2)
  end
end
