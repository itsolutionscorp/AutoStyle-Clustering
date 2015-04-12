def compute(one, two)
    # Difference between boths strands is 0 initially
    difference = 0

    0.upto(one.length) do |position|
      # Compare current position for both strands and increment the
      # difference by one if both strands differ here
      difference += 1 if one[position] != two[position]
    end

    # Return the computed difference
    difference
  end