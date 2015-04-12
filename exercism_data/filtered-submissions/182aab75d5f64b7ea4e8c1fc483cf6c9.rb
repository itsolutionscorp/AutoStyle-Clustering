def compute(first_strand, other_strand)
    # all from http://exercism.io/submissions/37d5469b10f25ed08292db23
    first_strand.chars.zip(other_strand.chars).count do |first, second|
      first && second && first != second
    end
  end