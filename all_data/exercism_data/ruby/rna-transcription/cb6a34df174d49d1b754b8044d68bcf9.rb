module Complement
  def self.r_map
    @r_map ||= { G: 'C', C: 'G', T: 'A', A: 'U' }
  end

  def self.d_map
    @d_map ||= r_map.invert
  end

  def self.of_dna(seq)
    seq.gsub(/#{r_map.keys.join('|')}/) { |m| r_map[m.to_sym] }
  end

  def self.of_rna(seq)
    seq.gsub(/#{d_map.keys.join('|')}/) { |m| d_map[m] }
  end
end
