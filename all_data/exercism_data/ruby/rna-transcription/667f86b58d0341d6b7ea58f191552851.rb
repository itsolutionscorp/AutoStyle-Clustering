class Complement
  @dna_table = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @rna_table = @dna_table.invert
  ['dna', 'rna'].each do |name|
    define_singleton_method "of_#{name}" do |str|
      str.scan(/\w/).map{|ch| eval("@#{name}_table[ch]")}.join
    end
  end
end
