class Hamming

  def self.compute(strand1, strand2)

    count  = 0
    bits   = strand1.split("")
    pieces = strand2.split("")

    for i in 0..bits.length

      if bits[i] != pieces[i]
        count += 1
      end

    end
      return count
  end

end
