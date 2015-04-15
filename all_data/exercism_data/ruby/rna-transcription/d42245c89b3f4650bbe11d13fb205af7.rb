class Complement
  def self.of_dna(d)
    m = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U', 'U' => 'A'}
    d.split('').map {|x|
      m[x]
    }.join("")
  end

  def self.of_rna(d)
    self.of_dna(d).split('').map {|x|
      if x == 'U'
        'T'
      else
        x
      end
    }.join('')
  end
end
