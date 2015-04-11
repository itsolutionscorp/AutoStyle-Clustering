class Hamming
  def self.compute(strand1, strand2)
    # Create array for each argument passed
    base1 = strand1.split(//)
    base2 = strand2.split(//)
    # Initial distance is zero
    distance = 0
    # Take first array and count items. Iterate based on count
    base1.count.times do |b|
      # If both arrays are of equal length AND are different characters, add 1 to distance
      if base1.count == base2.count && base1[b] != base2[b]
        distance += 1
      end
    end
    # Return new distance number
    return distance
  end
end
