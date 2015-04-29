#
# $File: complement.rb,v $
# $Date: 2014/12/29 23:20:22 $
# $Revision: ab5bf9b5ee84 $
#

class Complement

  private

  @@transform = Hash["
      G C
      C G
      T A
      A U
    ".split.each_slice(2).to_a
  ]

  def self.apply(tr)
    lambda{|str| str.upcase.chars.map{|x| tr[x]}.join}
  end

  @@of_dna = self.apply(@@transform)
  @@of_rna = self.apply(@@transform.invert)

  public

  def self.of_dna(str)
    @@of_dna.call(str)
  end

  def self.of_rna(str)
    @@of_rna.call(str)
  end

end
