def compute(first_strand, second_strand)
    first_strand    = first_strand.chars
    second_strand   = second_strand.chars
    difference      = 0

    first_strand.zip second_strand do |first, second|
      break if first.nil? || second.nil?
      difference    += 1 if first != second
    end

    return difference
  end