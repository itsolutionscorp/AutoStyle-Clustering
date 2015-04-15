module Complement

  DNA_COMPS = {'G'=>'C', 'C' =>  'G', 'A' => 'U', 'T' => 'A'}
  RNA_COMPS = {'C'=>'G', 'G' => 'C', 'U' => 'A', 'A' => 'T'}

  def self.of_dna(strand)
    strand.split("").map {|x| DNA_COMPS[x]}.join ""
  end

  def self.of_rna(strand)
    strand.split("").map {|x| RNA_COMPS[x]}.join ""
  end


end
