def compute(strand_one, strand_two)

    pairs = strand_one.size > strand_two.size ? strand_two.bytes.zip(strand_one.bytes) : strand_one.bytes.zip(strand_two.bytes)


    pairs.count{ |one, two| one != two }
  end