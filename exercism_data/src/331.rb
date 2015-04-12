def compute(a, b)
    return 0 unless a.length == b.length
    difference_count = 0 
    0.upto(a.length) do |index|
      unless a[index] == b[index]
        difference_count = difference_count + 1
      end
    end 
    difference_count
  end