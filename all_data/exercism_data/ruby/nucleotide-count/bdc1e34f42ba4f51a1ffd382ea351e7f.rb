module Molecules
  DNA_MOLECULES = %w(A T C G)
  RNA_MOLECULES = %w(A C G U)

  def molecule?(molecule)
    (DNA_MOLECULES + RNA_MOLECULES).uniq.include?(molecule)
  end
end

class DNA
  include Molecules

  def initialize(molecules)
    @molecules = molecules.scan(/\w/)
  end

  def count(molecule)
    if molecule?(molecule)
      molecule == 'U' ? 0 : nucleotide_counts[molecule]
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    @molecules.each_with_object(nucleotides) do |molecule, nucleotide|
      nucleotide[molecule] += 1
    end
  end

private
  def nucleotides
    Hash[Molecules::DNA_MOLECULES.map { |molecule| [molecule, 0] }]
  end

end
