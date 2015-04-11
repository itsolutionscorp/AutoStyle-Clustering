class Complement

  def self.of_dna(strand)
    rna = ''
    strand.length.times do |count|
      rna << case strand[count]
      when "G" then "C"
      when "C" then "G"
      when "T" then "A"
      when "A" then "U"
      end
    end
    rna
  end

  def self.of_rna(strand)
    dna = ''
    strand.length.times do |count|
      dna << case strand[count]
      when "G" then "C"
      when "C" then "G"
      when "A" then "T"
      when "U" then "A"
      end
    end
    dna
  end

end
