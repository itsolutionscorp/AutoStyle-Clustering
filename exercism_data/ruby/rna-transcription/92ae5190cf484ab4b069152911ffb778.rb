module Complement
  extend self

  def of_dna(dna_strand)
    dna_strand.chars.inject('') { |s, c| s << @rna_compliment[c] }
  end

  def of_rna(rna_strand)
    rna_strand.chars.inject('') { |s, c| s << @dna_compliment[c] }
  end

  private

  # noinspection RubyStringKeysInHashInspection
  @rna_compliment = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  # noinspection RubyStringKeysInHashInspection
  @dna_compliment = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A',
  }

end
