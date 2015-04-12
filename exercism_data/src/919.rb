def compute a,b
    a,b = b,a if a.length > b.length

    if a.empty?
      0
    else
      (a[0] == b[0] ? 0 : 1) + compute(a[1..-1], b[1..-1])
    end
  end

end