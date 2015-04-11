class Complement
  def self.of_dna(nuc)
    comp = ""
    nuc.each_char do |c|
      comp += case c
      when 'G' then 'C'
      when 'C' then 'G'
      when 'T' then 'A'
      else 'U'
      end
    end
    comp
  end

  def self.of_rna(nuc)
    comp = ""
    nuc.each_char do |c|
      comp += case c
      when 'C' then 'G'
      when 'G' then 'C'
      when 'U' then 'A'
      else 'T'
      end
    end
    comp
  end
end
