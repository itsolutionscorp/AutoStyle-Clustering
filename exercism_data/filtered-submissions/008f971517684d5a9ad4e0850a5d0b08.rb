def compute(strand1, strand2)

      strand1 = strand1.chars
      strand2 = strand2.chars


      strand1 = strand1.take(strand2.length) if strand1.length > strand2.length
      strand2 = strand2.take(strand1.length) if strand2.length > strand1.length


      strand1.zip(strand2).count { |strand1, strand2| strand1 != strand2 }
    end