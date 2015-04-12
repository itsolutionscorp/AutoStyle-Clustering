def compute(strand, different_strand)
    a = strand.split("")
    b = different_strand.split("")
    iterator = 0
    count = 0

    a.zip(b).each do |c, d|
      break if iterator == a.length || iterator == b.length
      if c != d
        count += 1
      end
      iterator += 1
    end
    count
  end