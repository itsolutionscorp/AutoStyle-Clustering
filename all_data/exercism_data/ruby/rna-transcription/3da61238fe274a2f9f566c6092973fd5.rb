class Complement

  Substitute = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
    }

  def self.of_dna(sequence)
    ans = ''
    sequence.split('').each do |base|
      ans += Substitute[base]
    end
    ans
  end

  def self.of_rna(sequence)
    ans = ''
    sequence.split('').each do |base|
      ans += Substitute.invert[base]
    end
    ans
  end

end
