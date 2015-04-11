#!/usr/bin/env ruby

class Complement

  def self.dna_mapper(n)
    case n
    when 'G'
      n = 'C'
    when 'C'
      n = 'G'
    when 'T'
      n = 'A'
    when 'A'
      n = 'U'
    end
    return n  
  end
  
  def self.rna_mapper(n)
    case n
    when 'C'
      n = 'G'
    when 'G'
      n = 'C'
    when 'A'
      n = 'T'
    when 'U'
      n = 'A'
    end
    return n
  end
  
  def self.of_dna(dna_strand)
    dna_ary = dna_strand.split('')
    dna_ary.map! do |d|
      Complement.dna_mapper(d)
    end

    return dna_ary.join

  end

  def self.of_rna(rna_strand)
    rna_ary = rna_strand.split('')
    rna_ary.map! do |r|
      Complement.rna_mapper(r)
    end

    return rna_ary.join
  end

end
