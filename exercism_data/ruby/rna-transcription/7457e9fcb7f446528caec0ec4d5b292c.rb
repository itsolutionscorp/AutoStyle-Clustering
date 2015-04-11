class Complement
  def self.complementor(dna=true)
    if dna
      return proc do |result, allele|
        result += case allele
                  when 'G'
                    'C'
                  when 'C'
                    'G'
                  when 'A'
                    'U'
                  when 'T'
                    'A'
                  end
      end
    else
      return proc do |result,allele|
        result += case allele
                  when 'G'
                    'C'
                  when 'C'
                    'G'
                  when 'A'
                    'T'
                  when 'U'
                    'A'
                  end
      end
    end
  end
  def self.of(string,dna=true)
    if dna
      string.each_char.reduce('', &complementor)
    else
      string.each_char.reduce('', &complementor(dna=false))
    end
  end
  
  def self.of_dna(arg)
    self.of(arg,dna=true)
  end
  
  def self.of_rna(arg)
    self.of(arg,dna=false)
  end
end
