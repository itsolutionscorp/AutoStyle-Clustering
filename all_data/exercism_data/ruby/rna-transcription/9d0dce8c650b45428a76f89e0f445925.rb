module ComplementByMap
  DNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  RNA = DNA.invert

  class << self

    def of_dna(strand)
      strand.chars.map(&DNA.method(:fetch)).join
    end
    
    def of_rna(strand)
      strand.chars.map(&RNA.method(:fetch)).join
    end

  end
end

module ComplementByTr
  DNA, RNA = "GCTA", "CGAU"

  class << self

    def of_dna(strand)
      strand.tr DNA, RNA
    end

    def of_rna(strand)
      strand.tr RNA, DNA
    end

  end
end

Complement = ComplementByTr
