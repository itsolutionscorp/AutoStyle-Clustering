def compute a, b
    #ignore extra length on one of the strings
    length = [a.length, b.length].min
    hamming = 0
    length.times do |i|
      hamming += 1 if a[i] != b[i]
    end
    hamming
  end