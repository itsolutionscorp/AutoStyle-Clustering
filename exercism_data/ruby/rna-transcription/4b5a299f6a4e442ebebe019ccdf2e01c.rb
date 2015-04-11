#
# $File: complement.rb,v $
# $Date: 2014/12/29 21:48:20 $
# $Revision: 76cf75d40fb2 $
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
