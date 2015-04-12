def compute(strand, different_strand)
    a = strand.split("")
    b = different_strand.split("")
    count = 0

    a.zip(b).first( a.length && b.length).each do |c, d|
      if c != d
        count += 1
      end
    end
    count
  end