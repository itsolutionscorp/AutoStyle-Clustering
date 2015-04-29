def compute(strandA, strandB)
    sum=0
    strandAB = [strandA, strandB].sort_by(&:length)
    short, long = strandAB.first, strandAB.last

    short.each_char.with_index do |c,i|
      sum += 1 if long[i] != c
    end
    return sum
  end