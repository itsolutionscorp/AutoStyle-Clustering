def compute(strand_a, strand_b)
    unless strand_a.length == strand_b.length
      raise ArgumentError.new 'Strands must be of equal length'
    end
    (0...strand_a.length).count { |i| i if strand_a[i] != strand_b[i] }
  end
end