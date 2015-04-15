class Complement

  def self.of_dna(nucleotide)

    string = ""
    array = nucleotide.split('')
    array.each do |x|
      case x
        when 'C'
          string << 'G'
        when 'G'
          string << 'C'
        when 'T'
          string << 'A'
        when 'A'
          string << 'U'
      end
    end
    string
  end

  def self.of_rna(nucleotide)

    string = ""
    array = nucleotide.split('')
    array.each do |x|
      case x
        when 'G'
          string << 'C'
        when 'C'
          string << 'G'
        when 'A'
          string << 'T'
        when 'U'
          string << 'A'
      end
    end
    string
  end
end
