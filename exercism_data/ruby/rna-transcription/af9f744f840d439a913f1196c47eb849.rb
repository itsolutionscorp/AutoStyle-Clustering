#
# $File: complement.rb,v $
# $Author: max <max.deineko@gmail.com> $
# $Date: 2014/12/29 21:28:20 $
# $Revision: ffeab756d035 $
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
    lambda{|str| str.upcase.split("").map{|x| tr[x]}.join}
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
