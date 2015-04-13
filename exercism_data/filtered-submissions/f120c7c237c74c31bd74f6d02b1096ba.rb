def compute(first_strand, second_strand)

    counter = 0

    min = [first_strand, second_strand].min_by { |strand| strand.length }

    number = min.length - 1


    0.upto(number) do |x|
      if first_strand[x] != second_strand[x]
        counter +=1
      end
    end

    counter

  end