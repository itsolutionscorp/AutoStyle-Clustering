class Complement

  def self.of_dna(a)
    transcribed = ""
    aa = a.split(//)

    aa.each { |val|
      transcribed += case
                  when val == 'G'
                    'C'
                  when val == 'C'
                    'G'
                  when val == 'T'
                    'A'
                  when val == 'A'
                    'U'
                  end
    }

    return transcribed

  end

  def self.of_rna(a)
    transcribed = ""
    aa = a.split(//)

    aa.each { |val|
      transcribed += case
                  when val == 'G'
                    'C'
                  when val == 'C'
                    'G'
                  when val == 'A'
                    'T'
                  when val == 'U'
                    'A'
                  end
    }

    return transcribed

  end

end
