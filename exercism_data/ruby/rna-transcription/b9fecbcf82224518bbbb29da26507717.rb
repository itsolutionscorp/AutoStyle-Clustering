class Complement
  def self.of_dna(s)
    ns = ''
    for i in 0...s.length
      case s[i]
        when 'G' then ns+='C'
        when 'C' then ns+='G'
        when 'T' then ns+='A'
        when 'A' then ns+='U'
      end
    end
    ns
  end

  def self.of_rna(s)
    ns = ''
    for i in 0...s.length
      case s[i]
        when 'C' then ns+='G'
        when 'G' then ns+='C'
        when 'A' then ns+='T'
        when 'U' then ns+='A'
      end
    end
    ns
  end
end
