class Hamming; end

def Hamming.compute(a, b)
  # We start with 0 differences
  diff = 0

  # Get the shortest strand lenght and fix strands
  length = [a, b].min_by(&:size).size
  a, b = a[0, length], b[0, length]

  # Increment diff for every missing char
  (0..(length)).each { |i| diff += 1 if a[i] != b[i] }

  diff
end
