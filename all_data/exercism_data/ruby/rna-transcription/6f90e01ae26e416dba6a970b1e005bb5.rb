class Complement
  def self.of_dna(strands)
    conversion_table = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    convert_nucleotides(strands, conversion_table)
  end

  def self.of_rna(strands)
    conversion_table = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A'}
    convert_nucleotides(strands, conversion_table)
  end

  private

  def self.convert_nucleotides(strands, conversion_table)
    strands.chars.inject('') do |acc , strand|
      acc << conversion_table[strand]
    end
  end
end
