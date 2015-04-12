def compute(a, b)
    return 0 if a == b # performance!
    
    max_length = [a.length, b.length].min
    a, b = a[0..max_length-1].split(''), b[0..max_length-1].split('')

    diffs = 0
    a.each_with_index do |element, ielement|
      diffs += 1 if a[ielement] != b[ielement]
    end
    diffs
  end