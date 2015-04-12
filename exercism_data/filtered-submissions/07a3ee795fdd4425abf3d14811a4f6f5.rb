class Hamming
  def compute(a, b)
    distance = 0
    as, bs = a.to_s, b.to_s

    alen = as.length
    blen = bs.length

    min = [alen, blen].min

    as = as[0..min-1]
    bs = bs[0..min-1]


    short, long = [as, bs].sort

    long.chars.zip(short.chars).each {|ac, bc| distance += 1 if ac != bc }

    distance
  end
end
