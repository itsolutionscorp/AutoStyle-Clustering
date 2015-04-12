class Hamming
  def compute(strand_a, strand_b)
    strand_a.split('').each.with_index.inject(0) do |count, (item, index)|
      item != strand_b[index] ? count + 1 : count
    end
  end
end
