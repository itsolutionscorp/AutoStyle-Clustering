def replaceDna chr
  if chr == 'C'
    return 'G'
  elsif chr == 'G'
    return 'C'
  elsif chr == 'T'
    return 'A'
  else
    return 'U'
  end
end

def replaceRna chr
  if chr == 'C'
    return 'G'
  elsif chr == 'G'
    return 'C'
  elsif chr == 'A'
    return 'T'
  else
    return 'A'
  end
end

class Complement
  def self.of_rna str
    ret = ""
    str.each_char do |chr|
      ret += replaceRna(chr)
    end
    ret
  end
  def self.of_dna str
    ret = ""
    str.each_char do |chr|
      ret += replaceDna(chr)
    end
    ret
  end
end
