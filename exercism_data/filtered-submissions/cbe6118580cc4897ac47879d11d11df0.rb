class Hamming
  def compute(dna_a, dna_b)
    result = 0
    [dna_a.length,dna_b.length].min.times do |i|
      result += dna_a[i] != dna_b[i] ? 1 : 0
    end
    return result
  end
end
