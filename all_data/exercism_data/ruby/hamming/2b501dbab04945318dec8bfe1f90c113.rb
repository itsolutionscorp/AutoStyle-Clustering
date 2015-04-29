module Hamming
  def self.compute(s1, s2)
    different_pairs(s1,s2).length
  end

  def self.different_pairs(s1, s2)
    a1 = strand_to_array(s1)
    a2 = strand_to_array(s2)

    # truncate to shorter strand's length...
    min = [a1,a2].map(&:length).min
    a2 = a2[0,min]
    a1 = a1[0,min]

    # ...and compare.
    a1.zip(a2).select { |(x,y)| x != y }
  end

  # Given array or string, we want an array.
  def self.strand_to_array(s)
    s.respond_to?(:to_a) ? s.to_a : s.split('')
  end

end
