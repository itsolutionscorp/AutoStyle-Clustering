class Complement
  DNA = 'GCTA'
  RNA = 'CGAU'

  def self.of_dna(org_strand)
    toggle_nucleotides org_strand, DNA, RNA
  end

  def self.of_rna(org_strand)
    toggle_nucleotides org_strand, RNA, DNA
  end

  def self.toggle_nucleotides(org_strand, from, to)
    invalid_nucleotide = org_strand.match('[^' + from + ']')
    fail ArgumentError.new(invalid_nucleotide.to_s + ' is not a valid nucleotide!') if invalid_nucleotide 
    org_strand.tr(from, to)
  end

  private_class_method :toggle_nucleotides
end
