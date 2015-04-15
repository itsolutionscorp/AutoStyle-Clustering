class Complement
  class << self
    def of_dna strand
      DNA.new(strand).tr('GCTA', 'CGAU')
    end

    def of_rna strand
      RNA.new(strand).tr('CGAU', 'GCTA')
    end
  end
end

class DNA < String
  def initialize strand
    strand.chars.map do |n|
      raise ArgumentError, "Illegal DNA Nucleotide: #{n}" unless ['G', 'C', 'T', 'A'].include? n
    end
    super
  end
end

class RNA < String
  def initialize strand
    strand.chars.map do |n|
      raise ArgumetError, "Illegal RNA Nucleotide: #{n}" unless ['G', 'C', 'U', 'A'].include? n
    end
    super
  end
end
