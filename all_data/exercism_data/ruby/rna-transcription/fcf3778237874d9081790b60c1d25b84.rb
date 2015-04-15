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
    fail ArgumentError.new('test') if org_strand.match('[^' + from + ']')
    org_strand.tr(from, to)
  end

  private_class_method :toggle_nucleotides
end
