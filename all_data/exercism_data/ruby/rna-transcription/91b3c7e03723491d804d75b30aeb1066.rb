module Complement
  DNA = "GCTA".chars
  RNA = "CGAU".chars

  def self.of_dna(a)
    translate(a, DNA, RNA)
  end

  def self.of_rna(a)
    translate(a, RNA, DNA)
  end

  def self.translate(a, from, to)
    a.gsub(/./) { |ch| to[from.index(ch)] }
  end
end
