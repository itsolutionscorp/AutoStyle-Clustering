class Complement
  def self.of_dna(d)
    d.split('').map {|x|
      case x
      when 'G'
        'C'
      when 'C'
        'G'
      when 'T'
        'A'
      when 'A'
        'U'
      when 'U'
        'A'
      end
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
