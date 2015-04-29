def compute(a, b)

    if a == b
      return 0
    end


    a_acids = a.split("")
    b_acids = b.split("")
    dist    = 0


    min_length = (a_acids.count <= b_acids.count) ? a_acids.count : b_acids.count


    a_acids.each_with_index do |c, i|
      if c != b_acids[i]
        dist += 1
      end


      break if min_length == i + 1
    end

    return dist
  end