class Complement

  def self.of_dna x
    of(x) { complements }
  end

  def self.of_rna x
    of(x) { inverted_complements }
  end

  private
  def self.of x
    hash = yield
    x.chars.map { |c| hash[c] }.join
  end

  def self.complements
    @@complements ||= {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.inverted_complements
    @@inverted_complements ||= complements.invert
  end
end
