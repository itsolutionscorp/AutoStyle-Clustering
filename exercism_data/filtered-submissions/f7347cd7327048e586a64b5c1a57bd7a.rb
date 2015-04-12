class Hamming
  def compute(dna1, dna2)
    # init counter for dna differences
    count = 0

    # loop over dna strand 1
    for i in 0..dna1.length-1 do

      # break execution if dna strand 1 is longer than
      # dna strand 2 (when the first position longer
      # than dna strand 2 has been reached)
      if (i >= dna2.length) then
        break
      end

      # for each position, compare with dna strand 2.
      # increase counter if they are not equal
      count = if (dna1[i] != dna2[i]) then count + 1 else count end
    end

    count
  end
end
