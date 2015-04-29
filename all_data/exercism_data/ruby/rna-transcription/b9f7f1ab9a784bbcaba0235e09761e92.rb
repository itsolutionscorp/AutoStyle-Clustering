class Complement
  @dna_to_rna = 'GCTACGAU'

  def self.of_dna(dna_str)
    if dna_str.split('').select { |x| !@dna_to_rna[0..3].split('').include? x }.empty?
      dna_str.each_char.map { |x| @dna_to_rna[@dna_to_rna[0..3].index(x) + 4] }.join
    else
      fail ArgumentError
    end
  end

  def self.of_rna(rna_str)
    if rna_str.split('').select { |x| !@dna_to_rna[4..-1].split('').include? x }.empty?
      rna_str.each_char.map { |x| @dna_to_rna[@dna_to_rna[4..-1].index(x)] }.join
    else
      fail ArgumentError
    end
  end
end

__END__

  DNA -> RNA
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
