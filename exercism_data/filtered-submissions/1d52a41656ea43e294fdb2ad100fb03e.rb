def compute(strand0, strand1)
    return 0 if strand0 == strand1

    minlen = [strand0.length, strand1.length].min
    arr0 = strand0.scan(/./)[0,minlen]
    arr1 = strand1.scan(/./)[0,minlen]

    arr0.zip(arr1).map { |p| p.first != p.last ? 1 : 0 }.reduce(:+)
  end