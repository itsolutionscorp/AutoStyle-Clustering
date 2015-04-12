def compute(first_strand, second_strand)

    counter = 0
    min_strand_length = [first_strand.length, second_strand.length].min
    min_strand_length.times do |number|
      if first_strand[number] != second_strand[number]
        counter += 1
      end
    end
    return counter

  end