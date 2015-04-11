module Complement
  extend self

  def of_dna(dna_strand)
    dna_strand.chars.inject('') { |s, n| s << COMPLIMENT[n] }
  end

  def of_rna(rna_strand)
    rna_strand.chars.inject('') { |s, n| s << COMPLIMENT.key(n)  }
  end

  private

  # noinspection RubyStringKeysInHashInspection
  COMPLIMENT = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

end
