class Hamming
  def self.compute(strand_1, strand_2)
    s1 = strand_1.split('');
    s2 = strand_2.split('');

    s1.zip(s2).count{ |v| v[0] != v[1] }
  end
end
