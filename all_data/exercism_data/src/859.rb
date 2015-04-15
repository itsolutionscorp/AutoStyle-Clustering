def compute(one, two)

    difference = 0

    0.upto(one.length) do |position|


      difference += 1 if one[position] != two[position]
    end


    difference
  end