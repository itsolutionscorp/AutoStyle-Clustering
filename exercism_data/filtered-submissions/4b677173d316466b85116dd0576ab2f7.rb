def compute(strand_a, strand_b)
    unless strand_a.length == strand_b.length
      raise Exception.new 'Strands must be of equal length'
    end
    (0...strand_a.length).collect do |i|
      strand_a[i] != strand_b[i] ? 1 : 0
    end.inject(:+)
  end
end