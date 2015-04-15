module Complement
  extend self

  def of_dna(dna_strand)
    dna_strand.chars.inject('') { |s, n| s << COMPLEMENT[n] }
  end

  def of_rna(rna_strand)
    rna_strand.chars.inject('') { |s, n| s << COMPLEMENT.key(n)  }
  end

  private

  # noinspection RubyStringKeysInHashInspection
  COMPLEMENT = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

end
