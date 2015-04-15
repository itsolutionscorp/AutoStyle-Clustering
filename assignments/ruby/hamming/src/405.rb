def compute(first_strand, other_strand)

    first_strand.chars.zip(other_strand.chars).count do |first, second|
      first && second && first != second
    end
  end