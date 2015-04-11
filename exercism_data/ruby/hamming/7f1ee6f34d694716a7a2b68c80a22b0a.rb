class Hamming
  def self.compute (strand1, strand2)
    # Iterate through each nucleobase in each strand and check if equal.
    # If not equal, increment dist to count dissimilarities.
    dist = 0

    (0..strand1.length).each do |i|
    dist += 1 unless strand1[i] == strand2[i]
    end

    return dist
  end
end
