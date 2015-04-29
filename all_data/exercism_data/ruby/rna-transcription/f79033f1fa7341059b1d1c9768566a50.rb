class Complement
  def self.of_dna(strand)
    strand.split(//).each_with_object([]) do |ntide, array|
      array << { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }[ntide]
    end.join
  end

  def self.of_rna(strand)
    strand.split(//).each_with_object([]) do |ntide, array|
      array << { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }[ntide]
    end.join
  end
end
