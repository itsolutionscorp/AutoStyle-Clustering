class Complement
  def self.of_dna(dna)
    ans = ''
    dna.split('').each do |letter|
      if letter == 'G'
        ans += 'C'
      elsif letter == 'C'
        ans += 'G'
      elsif letter == 'T'
        ans += 'A'
      elsif letter == 'A'
        ans += 'U'
      end
    end
    ans
  end

  def self.of_rna(rna)
    ans = ''
    rna.split('').each do |letter|
      if letter == 'C'
        ans += 'G'
      elsif letter == 'G'
        ans += 'C'
      elsif letter == 'A'
        ans += 'T'
      elsif letter == 'U'
        ans += 'A'
      end
    end
    ans
  end
end
