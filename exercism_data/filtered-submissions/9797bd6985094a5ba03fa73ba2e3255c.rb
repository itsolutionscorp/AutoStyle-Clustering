def compute(one, two)
    hamming = 0


    smallest = [one, two].min_by { |str| str.length }



    smallest.split('').each_index do |i|
      hamming += 1 if one[i] != two[i]
    end

    hamming
  end