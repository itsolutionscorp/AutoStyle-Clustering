class Complement
  def self.of_either(strand, complement)
    new_strand = ''

    strand.length.times do |index|
      if complement.has_key?( strand[index] ) then
        new_strand[index] = complement[ strand[index] ]
      else
        new_strand[index] = strand[index]
      end
    end

    new_strand
  end

  def self.of_rna(strand)
    complement = {
                    "C" => "G",
                    "G" => "C",
                    "A" => "T",
                    "U" => "A",
                 }

    self.of_either(strand, complement)
  end


  def self.of_dna(strand)
    complement = {
                    "G" => "C",
                    "C" => "G",
                    "T" => "A",
                    "A" => "U",
                 }

    self.of_either(strand, complement)
  end
end
