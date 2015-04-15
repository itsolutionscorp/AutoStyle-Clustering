require 'pry'
class Complement

  def self.of_dna(dna)
    answer = ''
    converters = { 'G' => 'C',
                  'C' => 'G',
                  'T' => 'A',
                  'A' => 'U' }
    dna.split('').each { |x| answer += converters[x]}
    answer
  end

  def self.of_rna(rna)
    answer = ''
    converters = { 'G' => 'C',
                  'C' => 'G',
                  'U' => 'A',
                  'A' => 'T' }
    rna.split('').each { |x| answer += converters[x]}
    answer
  end

end
