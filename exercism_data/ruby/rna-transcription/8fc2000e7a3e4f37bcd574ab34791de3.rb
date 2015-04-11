class Complement

  COMPLEMENTS_OF_DNA = {
    :G => "C",
    :C => "G",
    :T => "A",
    :A => "U"
  }

  COMPLEMENTS_OF_RNA = {
    :C => "G",
    :G => "C",
    :A => "T",
    :U => "A"
  }

  def self.method_missing(meth, *args, &block)
    unless meth.to_s =~ /of_\w{3}/
      fail NoMethodError.new
    else
      type = meth.to_s.match(/of_(\w{3})/)[1]
      strand = args[0].to_s
      process_nucleotides(strand, type)
    end
  end

  def self.respond_to?(method_sym, private=false)
    if method_sym.to_s =~ /of_[r|d]\w{2}$/
      true
    else
      super
    end
  end

  private
  def self.process_nucleotides(nucleotides, complement)
    transformed = ""
    method = "transform_#{complement}"
    nucleotides.each_char do |n|
      transformed << send(method, n)
    end
    transformed
  end

  def self.transform_dna(nucleotide)
    COMPLEMENTS_OF_DNA[nucleotide.to_sym]
  end

  def self.transform_rna(nucleotide)
    COMPLEMENTS_OF_RNA[nucleotide.to_sym]
  end
end
