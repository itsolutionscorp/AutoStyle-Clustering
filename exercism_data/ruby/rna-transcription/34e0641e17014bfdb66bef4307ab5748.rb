class Complement
  def self.of_dna rna
    convert(rna) { |nucleobase| rna_to_dna_converter nucleobase }
  end

  def self.of_rna dna
    convert(dna) { |nucleobase| dna_to_rna_converter nucleobase }
  end

  private

  def self.convert(base, &converter)
    complement = ''
    base.each_char do |nucleobase|
      complement << converter.call(nucleobase)
    end
    complement
  end

  def self.rna_to_dna_converter nucleobase
    case nucleobase
      when 'C'
        return 'G'
      when 'G'
        return 'C'
      when 'T'
        return 'A'
      when 'A'
        return 'U'
    end
  end

  def self.dna_to_rna_converter nucleobase
    case nucleobase
      when 'C'
        return 'G'
      when 'G'
        return 'C'
      when 'U'
        return 'A'
      when 'A'
        return 'T'
    end
  end
end
