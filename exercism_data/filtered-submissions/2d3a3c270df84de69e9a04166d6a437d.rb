class Hamming
  def compute(first, second)
    if first.length != second.length
      exit 1
    end

    count = 0

    for i in 0...first.length
      if first[i] != second[i]
        count += 1
      end
    end

    return count
  end
end
