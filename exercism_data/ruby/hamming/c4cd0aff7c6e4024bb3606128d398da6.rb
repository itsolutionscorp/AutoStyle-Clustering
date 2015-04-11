class Hamming
  def self.compute(a, b)

    distance = 0

    a.size.times do |index|
      if a[index].nil? || b[index].nil? then return distance end
      if a[index] != b[index] then distance += 1 end
    end

    return distance

  end
end
