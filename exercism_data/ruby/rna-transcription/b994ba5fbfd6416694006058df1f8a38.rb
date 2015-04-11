class Complement
  def self.of_dna(x)
    complement = []
    strand = x.split('')
    strand.each do |i|
      if i == 'A'
        complement.push('U')
      elsif i == 'T'
        complement.push('A')
      elsif i == 'C'
        complement.push('G')
      elsif i == 'G'
        complement.push('C')
      end
      end

      complement.join('')
      end

   def self.of_rna(x)
    complement = []
    strand = x.split('')
    strand.each do |i|
      if i == 'U'
      complement.push('A')
      elsif i == 'A'
      complement.push('T')
      elsif i == 'G'
      complement.push('C')
      elsif i == 'C'
      complement.push('G')
      end
      end
      complement.join('')
      end


end
