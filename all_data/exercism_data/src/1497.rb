def compute(strand_a, strand_b)
      strand_a.chars.select.with_index do |gene, i|
        gene != strand_b[i]
      end.size
    end