class Complement
  def self.of_dna(strand)
    strand.each_char.map { |base| rewrite(base) }.join
  end

  private

  def self.rewrite(base)
    rewrite_rules[base]
  end

  def self.rewrite_rules
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U',
    }
  end
end
