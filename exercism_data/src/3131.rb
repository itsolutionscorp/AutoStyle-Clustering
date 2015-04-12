class Hamming
  def compute arg1, arg2

    return 0 if arg1 == arg2

    distance = 0

    for i in 0..arg1.length
      if arg1[i] != arg2[i]
        distance += 1
      end
    end

    distance
  end
end
