class Complement

  @complements = [['G','C'],['C','G'],['T','A'],['A','U']]

  def self.convert (strand, type)
    sequence = ""

    strand.chars.each do |nucleo|
      @complements.each do |pair|
        if nucleo == pair[(type == :dna ? 0 : 1)]
          sequence += pair[(type == :dna ? 1 : 0)]
        end
      end
    end

    sequence
  end

  def self.of_dna(dna)
    convert(dna, :dna)
  end

  def self.of_rna(rna)
    convert(rna, :rna)
  end

end
