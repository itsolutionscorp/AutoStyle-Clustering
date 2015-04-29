class Hamming
  def self.compute(a, b)
    distance = 0
    index = 0

    while true
      unless a[index] && b[index] # Checks if end of either input has been reached
        return distance
      end

      distance = distance + 1 if a[index] != b[index]

      index = index + 1
    end
  end
end
