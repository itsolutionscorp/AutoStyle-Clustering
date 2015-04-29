class Complement
  def self.of_dna(rna_strand)
    result_dna = []
    rna_strand_array = rna_strand.split('')
    rna_strand_array.each do |x|
      result_dna << find_complement(x)
    end
    result_dna.join()
  end

  def self.of_rna(dna_strand)
    find_exception_complement(of_dna(dna_strand))
  end

  def self.find_complement(x)
    case x
      when 'C' then 'G'
      when 'G' then 'C'
      when 'T' then 'A'
      when 'A' then 'U'
      when 'U' then 'A'
    end
  end

  def self.find_exception_complement(string)
    string.gsub!('U', 'T') if string.include?('U')
    string
  end
end
