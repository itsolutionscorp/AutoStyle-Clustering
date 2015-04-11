class Hamming
  def self.compute(strand1, strand2)
    # if the strings are identical, no need
    # to test further
    if strand1 === strand2
      0
    end

    diff = 0

    # run through the supplied strings and
    # find the number of differences
    strand1.each_char.with_index do |character, index|
      if character != strand2[index]
        diff += 1
      end
    end

    diff
  end
end
