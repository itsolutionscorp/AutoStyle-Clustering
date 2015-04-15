class Hamming
  def self.compute stranda, strandb
    self.count_mistakes cap_to_min_size([stranda, strandb])
  end

  def self.count_mistakes strands
    zip_strands(strands).flat_map { |p| p[0] == p[1] }.count(false)
  end

  def self.cap_to_min_size strands
    len = [strands[0].size, strands[1].size].min
    strands.map { |s| s[0, len] }
  end

  def self.zip_strands strands
    strands[0].chars.zip strands[1].chars
  end
end
