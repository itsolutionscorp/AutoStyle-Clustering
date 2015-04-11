class Complement
  XREF = {"dna" => {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}, 
          "rna" => {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'} }

  XREF.keys.each do |strand_type|
    define_singleton_method "of_#{strand_type}" do |strand|
      strand.split('').map{|nucleotide| XREF[strand_type][nucleotide]}.join('')
    end
  end
end
