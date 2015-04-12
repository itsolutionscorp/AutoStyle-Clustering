class Hamming
  def compute(str_a, str_b)

    min_size = [str_a.size, str_b.size].min

    distance = 0

    min_size.times do |index|
      distance += 1 if str_a[index] != str_b[index]
    end

    return distance

  end
end
