# Steffen Parratt (username Neffetski) 14 Nov 2014
# Exercism.io exercise 3, version 1

module Complement

  # Given a DNA strand d, return its RNA complement
  def self.of_dna (d)
    d.length.times do |i| 
      case d[i]
      when 'G'
        d[i] = 'C'
      when 'C'
        d[i] = 'G'
      when 'T'
        d[i] = 'A'
      when 'A'
        d[i] = 'U'
      else
      end
    end
    return d  # dna complement
  end
  
  # Given a RNA strand r, return its DNA complement
  def self.of_rna (r)
    r.length.times do |i| 
      case r[i]
      when 'C'
        r[i] = 'G'
      when 'G'
        r[i] = 'C'
      when 'A'
        r[i] = 'T'
      when 'U'
        r[i] = 'A'
      else
      end
    end
    return r  # rna complement
  end

end
