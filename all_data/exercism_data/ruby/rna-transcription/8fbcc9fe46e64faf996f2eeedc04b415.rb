class Complement
  def self.of_dna(strand)
    complements = {g: 'C', c: 'G', t: 'A', a: 'U'}
    raise ArgumentError if strand.include? 'U'
    arr = strand.split('').to_a.map! { |i| complements[i.downcase.to_sym] }
    arr.join
  end

  def self.of_rna(strand)
    complements = {c: 'G', g: 'C', a: 'T', u: 'A'}
    raise ArgumentError if strand.include? 'T'
    arr = strand.split('').to_a.map! { |i| complements[i.downcase.to_sym] }
    arr.join
  end

end
