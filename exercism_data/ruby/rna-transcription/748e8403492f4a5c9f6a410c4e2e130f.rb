class Complement
  def self.of_dna strand
    strand.split('').map do |char|
      case char
        when 'G' then 'C'
        when 'C' then 'G'
        when 'T' then 'A'
        when 'A' then 'U'
      end
    end.join
  end

  def self.of_rna strand
    strand.split('').map do |char|
      case char
        when 'G' then 'C'
        when 'C' then 'G'
        when 'A' then 'T'
        when 'U' then 'A'
      end
    end.join
  end
end
