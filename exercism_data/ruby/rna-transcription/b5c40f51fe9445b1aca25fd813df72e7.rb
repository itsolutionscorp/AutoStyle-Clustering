class Complement
  @@subs = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna)
    dna.gsub(/[GCTA]+/) { |c| @@subs.fetch(c) }
  end
end
