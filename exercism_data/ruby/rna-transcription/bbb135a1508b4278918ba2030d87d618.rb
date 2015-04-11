class Complement

  @letters = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
      }

  def self.of_dna(dna)
    ans = ''
    dna.split('').each do |character|
      @letters.each do |k, v|
        if character == k
          ans += v
        end
      end
    end
    ans
  end

  def self.of_rna(rna)
    ans = ''
    rna.split('').each do |character|
      @letters.each do |k, v|
        if character == v
          ans += k
        end
      end
    end
    ans
  end
end
