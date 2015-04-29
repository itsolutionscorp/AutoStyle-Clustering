def compute(strandA, strandB)
    nucleids_a = strandA.chars
    nucleids_b = strandB.chars
    nucleids_a.zip(nucleids_b).reduce(0) do |sum, (a, b)|
      if a && b && a != b
        sum + 1
      else
        sum
      end
    end
  end