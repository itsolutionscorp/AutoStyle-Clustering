class Hamming
  def self.compute(strand1, strand2)
    # Initialize Hamming, iterator variables
    h, i = 0, 0

    # Turn each strand into an array
    strand1 = strand1.split(//)
    strand2 = strand2.split(//)

    # Make both strands the same length
    if strand1.length > strand2.length
      strand1 = strand1.slice(0..strand2.length-1)
    elsif strand2.length > strand1.length
      strand2 = strand2.slice(0..strand1.length-1)
    end

    # Iterate through strand1, incrementing
    # the Hamming distance with each inequality
    strand1.each do |x|
      if x != strand2[i]
        h += 1
      end
      i += 1
    end

    # Return the Hamming distance
    return h
  end
end
