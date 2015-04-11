class Complement
  def self.of_dna(string)
    self.new.match(string, true)
  end

  def self.of_rna(string)
    self.new.match(string, false)
  end

  def match(string, dna)
    hash = { 'G' => 'C',
             'C' => 'G',
             'T' => 'A',
             'A' => 'U' }
    mapped = string.split('').map do |char|
      dna ? hash[char] : hash.key(char)
    end
    mapped.join('')
  end
end
