class Hamming
  def self.compute(a, b)
    # We can immediately return if the strands are the same
    if a == b
      return 0
    end

    # Otherwise we split the strings
    a_acids = a.split("")
    b_acids = b.split("")
    dist    = 0

    # Figure out the length of the shorter strand
    min_length = (a_acids.count <= b_acids.count) ? a_acids.count : b_acids.count

    # And compare the strands
    a_acids.each_with_index do |c, i|
      if c != b_acids[i]
        dist += 1
      end

      # ignore longer strands
      break if min_length == i + 1
    end

    return dist
  end
end
