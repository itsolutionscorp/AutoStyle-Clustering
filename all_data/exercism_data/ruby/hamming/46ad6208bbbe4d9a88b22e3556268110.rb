class Hamming
  def self.compute(a, b)
    distance = 0
    index = 0

    while true
      unless a[index] && b[index] # Checks if end of either input has been reached
        return distance
      end

      distance += 1 if a[index] != b[index]

      index += 1
    end
  end
end
