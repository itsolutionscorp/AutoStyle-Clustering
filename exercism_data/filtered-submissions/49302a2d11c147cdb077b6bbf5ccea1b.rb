# hamming DNA sequence

class Hamming
  def compute(a, b)
    # put the length of each arg into an array and return the shortest (min)
    length = [a.length, b.length].min
    difference = 0
    # loop over length.times comparing a to b, increment difference if we don't match
    length.times do |i|
      diff += 1 if a[i] != b[i]
    end
    # return the difference
    difference
  end
end
