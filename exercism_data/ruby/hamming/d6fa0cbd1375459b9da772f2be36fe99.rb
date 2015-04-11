class Hamming

  def self.compute(strand1, strand2)

    count  = 0
    bits   = strand1.split("")
    pieces = strand2.split("")

    (0..bits.length).each do |n|
      if bits[n] != pieces[n]
        count+=1
      end
    end

    return count

  end
end
