module Complement
  def self.of_dna(rna)
    rna.chars.map do |c|
      case c.upcase
      when 'C' then 'G'
      when 'G' then 'C'
      when 'T' then 'A'
      when 'A' then 'U'
      else raise ArgumentError
      end
    end.join
  end

  def self.of_rna(dna)
    dna.chars.map do |c|
      case c.upcase
      when 'C' then 'G'
      when 'G' then 'C'
      when 'U' then 'A'
      when 'A' then 'T'
      else raise ArgumentError
      end
    end.join
  end
end
