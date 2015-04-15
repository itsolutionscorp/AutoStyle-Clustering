class Complement
  RNA_HASH = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"} 
  DNA_HASH = RNA_HASH.invert

  def self.of_dna(a)
    rna = ""

    a.length.times do |i|
      rna += RNA_HASH[a[i]]
    end

    rna
  end

  def self.of_rna(a)
    dna= ""

    a.length.times do |i|
      dna += DNA_HASH[a[i]]
    end

    dna
  end
end
