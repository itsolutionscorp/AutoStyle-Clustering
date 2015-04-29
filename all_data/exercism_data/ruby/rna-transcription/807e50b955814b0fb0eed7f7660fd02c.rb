class Complement

  def self.of_dna(genes)

    each_gene = genes.split("")
    complement = ""

    each_gene.each { |gene| complement += find_complement(gene) }

    complement
  end

  def self.of_rna(genes)

    each_gene = genes.split("")
    complement = ""

    each_gene.each { |gene| complement += find_complement(gene, false) }

    complement
  end

  def self.find_complement(gene, dna=true)
    if gene == "G"
      "C"
    elsif gene == "C"
      "G"
    elsif gene == "A" && dna == false
      "T"
    elsif gene == "A" && dna == true
      "U"
    elsif gene == "U"
      "A"
    elsif gene == "T"
      "A"
    end
  end

end
