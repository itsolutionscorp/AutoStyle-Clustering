def compute(strand_a,strand_b)
    strand_a.chars.zip(strand_b.chars).count do |eigen0, eigen1| 
      eigen0 != eigen1
    end
  end