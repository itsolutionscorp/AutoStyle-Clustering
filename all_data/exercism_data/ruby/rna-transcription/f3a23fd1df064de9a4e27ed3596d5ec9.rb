class Complement
  @b = "GCTAhjkl"
  @c = "hjklCGAU"

  def self.of_dna(a)
    8.times { |n| a = a.gsub(@b[n], @c[n])}
    a
  end

  def self.of_rna(a)
    8.times { |n| a = a.gsub(@c.reverse[n], @b.reverse[n])}
    a
  end
end
