class Hamming
  attr_accessor :hamminga, :hamminga

  def self.compute(a,b)
    @hammingb = b.chars
    @hamminga = a.chars

    @hamminga.zip(@hammingb.select{|hammingA,hammingB| hammingA != hammingB && (hammingA.nil? || hammingB.nil? == false )}).count
  end
end
