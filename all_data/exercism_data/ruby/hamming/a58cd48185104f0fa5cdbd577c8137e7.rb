class Hamming

  def self.compute(dna1,dna2)
    length = [ dna1.length, dna2.length ].min - 1
    ( 0..length ).inject( 0 ) do | diff_count, i |
      diff_count += 1 unless dna1[i] == dna2[i]
      diff_count
    end

  end
end
