def compute(a, b)
    return 0 if a == b
    shortestlength = [a, b].min.length - 1

    (0..shortestlength).count do |nt|
      a[nt] != b[nt]
    end
  end