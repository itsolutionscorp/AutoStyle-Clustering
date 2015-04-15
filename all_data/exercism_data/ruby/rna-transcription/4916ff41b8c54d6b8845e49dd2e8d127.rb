module Complement
  def self.r_map
    @r_map ||= { G: 'C', C: 'G', T: 'A', A: 'U' }
  end

  def self.r_pattern
    @r_pattern ||= Regexp.new(r_map.keys.join('|'))
  end

  def self.d_map
    @d_map ||= r_map.invert
  end

  def self.d_pattern
    @d_pattern ||= Regexp.new(d_map.keys.join('|'))
  end

  def self.of_dna(seq)
    seq.gsub(r_pattern) { |m| r_map[m.to_sym] }
  end

  def self.of_rna(seq)
    seq.gsub(d_pattern) { |m| d_map[m] }
  end
end
