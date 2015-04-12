# Compute the Hamming distance by counting the number of places
# where the strings a and b are different
class Hamming
  def compute(a, b)
    fail ArgumentError, 'Sequences must be the same length' if a.length != b.length

    (0...a.length).count { |i| a[i] != b[i] }
  end
end
