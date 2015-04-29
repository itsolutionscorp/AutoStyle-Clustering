def compute(first_strand, second_strand)

    counter = 0

    unless first_strand == second_strand
      (0...[first_strand.length, second_strand.length].min).each do | i |

        if first_strand[i] != second_strand[i]
         counter += 1
        end

      end
    end

    counter
  end