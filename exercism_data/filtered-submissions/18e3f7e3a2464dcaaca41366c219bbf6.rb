def compute(a, b)
    strand1 = a.split("")
    strand2 = b.split("")
    count = 0

    if strand1.length == strand2.length
      small = strand1
      big = strand2
    else
      (strand1.length < strand2.length) ? small = strand1 : small = strand2
      (strand1.length > strand2.length) ? big = strand1 : big = strand2
    end

    small.each_with_index do |ele, ind|
      small[ind] == big[ind] ? count : count += 1
    end
    count
  end