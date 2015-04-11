class Complement
  MASH = { 'G' => 'C',
           'C' => 'G',
           'T' => 'A',
           'A' => 'U'
          }

  def self.of_dna strand
    arrg = strand.split ""
    arr = []
    arrg.map do |i|
      arr << MASH.fetch(i)
    end
    arr.join("")
  end

  def self.of_rna strand
    arrg = strand.split ""
    arr = []
    arrg.map do |i|
      arr << MASH.key(i)
    end
    arr.join("")
  end
 #  puts self.of_dna("GCTA")
 #  puts self.of_rna("CGAU")
end
