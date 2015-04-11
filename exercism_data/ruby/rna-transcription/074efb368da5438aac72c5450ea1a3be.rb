module Complement

  def self.of_dna(str)
    str.each_char.inject('') do |rseq, dnuc|
      rseq.concat({ 'G' => 'C', 'C' => 'G',
                    'T' => 'A', 'A' => 'U' }.fetch(dnuc))
    end
  end

  def self.of_rna(str)
    str.each_char.inject('') do |dseq, rnuc|
      dseq.concat({ 'G' => 'C', 'C' => 'G',
                    'U' => 'A', 'A' => 'T' }.fetch(rnuc))
    end
  end

end
