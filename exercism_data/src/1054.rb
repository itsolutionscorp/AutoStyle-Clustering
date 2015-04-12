def compute(strand1, strand2)
    pairs = strand1.chars.zip(strand2.chars)
    pairs.count { |old,new| new && old != new }
  end