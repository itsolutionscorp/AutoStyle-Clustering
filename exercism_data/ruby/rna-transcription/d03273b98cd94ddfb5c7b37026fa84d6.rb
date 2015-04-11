class Complement
    PAIRS = { 'G' => 'C',
              'C' => 'G',
              'T' => 'A',
              'A' => 'U'
    }
  def self.of_dna(s)
    s.chars.map { |c| PAIRS[c] }.inject(:+)
  end

  def self.of_rna(s)
    s.chars.map { |c| PAIRS.key(c) }.inject(:+)
  end
end
