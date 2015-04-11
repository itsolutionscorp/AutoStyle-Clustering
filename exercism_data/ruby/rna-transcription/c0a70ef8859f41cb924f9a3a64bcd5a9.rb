class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(str)
    comp = str.chars.map{ |c| DNA_TO_RNA[c] }.join('')
    raise ArgumentError if str.length != comp.length
    comp
  end
  def self.of_rna(str)
    comp = str.chars.map{ |c| RNA_TO_DNA[c] }.join('')
    raise ArgumentError if str.length != comp.length
    comp
  end
end
