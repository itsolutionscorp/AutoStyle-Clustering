require 'pry'
class Complement
  def self.of_dna(sequence)
    translation = ''

    sequence.chars.each do |nucleotide|
      translation << translate(nucleotide, 'rna', 'dna')
    end

    translation
  end

  def self.of_rna(sequence)
    translation = ''

    sequence.chars.each do |nucleotide|
      translation << translate(nucleotide, 'dna', 'rna')
    end

    translation
  end

  private

  def self.translate(nucleotide, source, target)
    dictionary = { 'rna' => 'CGTA', 'dna' => 'GCAU' }

    index = dictionary[source].chars.index(nucleotide)
    dictionary[target].chars[index]
  end
end
