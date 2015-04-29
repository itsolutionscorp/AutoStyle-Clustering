def compute(strand_a, strand_b)
    strand_a = strand_a.split('')
    strand_b = strand_b.split('')
    strand_a.zip(strand_b).count do |x,y| 
      unless x == nil || y == nil
        x != y
      end
    end
  end