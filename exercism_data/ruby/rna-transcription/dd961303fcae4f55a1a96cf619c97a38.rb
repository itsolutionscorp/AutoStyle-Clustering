class Complement

  COMPLEMENT_OF_DNA = {g: 'C', c: 'G', t: 'A', a: 'U'}

  def self.of_dna(sequence)
    sequence.split(//).collect{ |n| COMPLEMENT_OF_DNA[n.downcase.to_sym] }.join
  end

  def self.of_rna(sequence)
    sequence.split(//).collect{ |n| COMPLEMENT_OF_DNA.key(n).to_s.upcase}.join
  end
end
