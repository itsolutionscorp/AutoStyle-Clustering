class Complement
  def self.of_dna(base_chain)
    converted = ''
    base_chain.each_char do |c|
      if c == 'A'
        converted += 'U'
      else
        converted += change_nucleotide(c)
      end
    end
    converted
  end

  def self.of_rna(base_chain)
    converted = ''
    base_chain.each_char do |c|
      if c == 'U'
        converted += "A"
      else
        converted += change_nucleotide(c)
      end
    end
    converted
  end

  def self.change_nucleotide(base)
    case base
    when 'C' then 'G'
    when 'G' then 'C'
    when 'T' then 'A'
    when 'A' then 'T'
    end
  end
end
