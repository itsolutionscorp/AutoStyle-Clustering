class Hamming

  def compute(n1, n2)

    n1_array = n1.split("")
    n2_array = n2.split("")

    distance = 0

    n1_array.length.times do |check|

      index = check - 1

      if n1_array[index] != n2_array[index]
        distance += 1
      end

    end

    return distance

  end

end
