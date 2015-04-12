def compute(first_strand, second_strand)

    if first_strand.length <= second_strand.length
      min_number = first_strand.length
    else
      min_number = second_strand.length
    end

    counter = 0

    min_number.times do |number|
      counter+=1 if first_strand[number] != second_strand[number]
      end
      return counter
    end