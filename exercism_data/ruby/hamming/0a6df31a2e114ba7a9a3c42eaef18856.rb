class Hamming
  def self.compute(first, second)
    if first.length != second.length
      raise "Different length!"
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
