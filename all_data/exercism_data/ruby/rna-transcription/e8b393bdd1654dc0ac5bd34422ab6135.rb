class Complement
  DNA_MAP = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  RNA_MAP = {'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T'}

  def self.of_dna(sequence)
    map_sequence(sequence, DNA_MAP)
  end

  def self.of_rna(sequence)
    map_sequence(sequence, RNA_MAP)
  end

  private

  def self.map_sequence(sequence, code)
    sequence.split('').collect do |letter|
      code[letter]
    end.join
  end
end
