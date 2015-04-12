class Hamming
  class << self
    def compute(strand1, strand2)
      # Remove extra chars if one of the strings is longer...
      if strand1.length > strand2.length
        strand1 = strand1[0..strand2.length-1]
      end

      if strand2.length > strand1.length
        strand2 = strand2[0..strand1.length-1]
      end

      # Split the strand strings to arrays
      strand1 = strand1.split(//)
      strand2 = strand2.split(//)

      # Calculate differences
      cnt = 0
      strand1.zip(strand2).each do |strand1, strand2|
        cnt += 1 if strand1 != strand2
      end
      cnt
    end
  end
end