class Complement
  def self.of_dna(strand)
    hash = { 
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
    }
    (0...strand.length).each { |i| strand[i] = hash[strand[i]] }
    strand
  end
  
  def self.of_rna(strand)
    hash = {
      'C' => 'G',
      'G' => 'C',
      'U' => 'A',
      'A' => 'T'
    }
    (0...strand.length).each { |i| strand[i] = hash[strand[i]] }
    strand
  end
end
