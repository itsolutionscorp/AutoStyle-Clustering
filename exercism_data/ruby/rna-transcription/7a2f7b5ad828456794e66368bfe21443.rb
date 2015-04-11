class Complement

  RNA_FROM_DNA = {
    G: 'C',
    C: 'G',
    T: 'A',
    A: 'U'
  }

  DNA_FROM_RNA = {
    C: 'G',
    G: 'C',
    A: 'T',
    U: 'A'
  }

  def self.of_dna dna
    translate RNA_FROM_DNA, dna
  end

  def self.of_rna rna
    translate DNA_FROM_RNA, rna
  end

private

  def self.translate from_to, sequence
    result = ""
    sequence.each_char do |c|
      result += from_to[c.to_sym]
    end
    result
  end

end
