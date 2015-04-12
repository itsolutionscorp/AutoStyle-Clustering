class Hamming

  def compute(dna1,dna2)
    length = [ dna1.length, dna2.length ].min - 1

    ( 0..length ).inject( 0 ) do | diff_count, i |
      dna1[i] == dna2[i] ? diff_count : diff_count += 1
    end

  end
end
