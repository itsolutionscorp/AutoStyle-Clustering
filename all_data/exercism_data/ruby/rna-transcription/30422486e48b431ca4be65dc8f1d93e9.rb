module Complement
  def self.translate_rna_base
    lambda do |base|
      case base
      when 'G'
        'C'
      when 'C'
        'G'
      when 'A'
        'T'
      when 'T'
        'A'
      when 'U'
        'A'
      end
    end
  end

  def self.translate_dna_base
    lambda do |base|
      case base
      when 'G'
        'C'
      when 'C'
        'G'
      when 'A'
        'U'
      when 'T'
        'A'
      end
    end
  end

  def self.translate(sequence, transform)
    sequence.chars.reduce('') do |agg, base|
      agg += transform.call(base)
    end
  end

  def self.of_dna(sequence)
    translate(sequence, translate_dna_base)
  end

  def self.of_rna(sequence)
    translate(sequence, translate_rna_base)
  end
end
