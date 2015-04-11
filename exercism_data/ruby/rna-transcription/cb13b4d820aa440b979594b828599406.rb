class Complement
  def self.of_dna(strand)
    strand.split(//).map {|s|
      case s
      when 'C' then 'G'
      when 'G' then 'C'
      when 'T' then 'A'
      when 'A' then 'U'
      end
    }.join
  end

  def self.of_rna(strand)
    strand.split(//).map {|s|
      case s
      when 'G' then 'C'
      when 'C' then 'G'
      when 'A' then 'T'
      when 'U' then 'A'
      end
    }.join
  end
end
