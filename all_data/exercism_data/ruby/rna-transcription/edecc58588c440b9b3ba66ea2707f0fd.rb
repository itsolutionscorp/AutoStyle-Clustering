class Complement
  def self.of_dna(strand)
    strand.chars.each_with_index.count do |letter, i|
      strand[i] = case letter
        when 'A' then 'U'
        when 'T' then 'A'
        when 'C' then 'G'
        when 'G' then 'C'
      end
    end 
    return strand
  end

  def self.of_rna(strand)
    strand.chars.each_with_index.count do |letter, i|
      strand[i] = case letter
        when 'U' then 'A'
        when 'A' then 'T'
        when 'C' then 'G'
        when 'G' then 'C'
      end
    end
    return strand
  end

end
