def compute (a,b)
    firstStrand = Array.new(a.split(//))
    secondStrand = Array.new(b.split(//))
    i = 0
    x = 0
    for i in i..a.length
      x += 1  if firstStrand[i] != secondStrand[i]
    end
    x
  end