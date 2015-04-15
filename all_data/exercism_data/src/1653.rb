def compute(strand1, strand2)
    diff = 0
    arr = strand1.split("").zip(strand2.split(""))
    arr.each { |x| diff += 1 unless x[0] == x[1] }
    diff
  end