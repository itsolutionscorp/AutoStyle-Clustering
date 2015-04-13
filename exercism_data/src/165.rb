def compute(strand1, strand2)
    differences = []
    strand1.length.times do | idx |
      differences << strand1[idx] if strand1[idx] != strand2[idx]
    end
    differences.length
  end