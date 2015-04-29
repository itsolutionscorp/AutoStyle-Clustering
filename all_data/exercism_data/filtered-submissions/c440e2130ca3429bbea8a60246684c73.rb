def compute(a,b)
    a = a.slice(0, b.length) if a.length > b.length
    b = b.slice(0, a.length) if b.length > a.length

    c = a.split(//).zip b.split(//)
    c.map{|d,e| d == e ? 0 : 1}.inject(:+)
  end