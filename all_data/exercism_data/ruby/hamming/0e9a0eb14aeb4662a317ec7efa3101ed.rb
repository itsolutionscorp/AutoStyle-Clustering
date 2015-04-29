class Hamming

  def self.compute(a, b)
    return 0 if a == b
    compare_arrays(*[a, b].map(&:chars))
  end

  private

  def self.compare_arrays(a, b)
    a.zip(b).reduce(0) do |count, pair|
      if (pair[0] != pair[1]) && !pair.any?(&:nil?)
        count.next
      else
        count
      end
    end
  end

end
