def compute(first_strand, second_strand)






    first_strand.chars.zip(second_strand.chars).count do |pair|
      pair[0] != pair[1]
    end
  end