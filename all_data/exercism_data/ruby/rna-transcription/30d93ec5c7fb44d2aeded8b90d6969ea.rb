module Complement
  extend self

  def rna_pairs
    {
      'A' => 'U',
      'C' => 'G'
    }
  end

  def dna_pairs
    {
      'A' => 'T',
      'C' => 'G'
    }
  end

  def of_dna(base)
    complement_of(base, dna_pairs)
  end

  def of_rna(base)
    complement_of(base, rna_pairs)
  end

  def complement_of(base, pairs)
    base.split('').map do |c|
      pair_of(c, pairs)
    end.join
  end

  def pair_of(base, base_pairs)
    base_pairs.inject(nil) do |complement, pairs|
      if pairs.include?(base)
        complement = pairs.find {|base_in_pair| base_in_pair != base }
      end
      complement
    end
  end

end
