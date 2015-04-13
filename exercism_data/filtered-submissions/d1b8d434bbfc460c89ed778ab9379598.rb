def compute(a, b)
    hamming = 0


    smallest = [a, b].min_by(&:length)



    smallest.split('').each_index do |i|
      hamming += 1 if a[i] != b[i]
    end

    hamming
  end