class Complement
  DNA = {
    'G' => 'C',
    'C' => 'G',
    'T'=> 'A',
    'A'=> 'U'
  }

  RNA = {
    'G' => 'C',
    'C' => 'G',
    'A' => 'T',
    'U' => 'A'
  }


  def self.of_dna(dna)
    dna.chars.map { |string| DNA.fetch(string) }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |string| RNA.fetch(string) }.join
  end
end
