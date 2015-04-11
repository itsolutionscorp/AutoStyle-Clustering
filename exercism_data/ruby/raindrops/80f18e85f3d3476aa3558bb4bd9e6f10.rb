require 'prime'

class Raindrops
  attr_reader :num
  
  def self.convert(num)
    Raindrops.new(num).convert
  end
  
  def initialize(num)
    @num = num
  end
  
  def convert
    construct_string(prime_factors)
  end
  
  private
  
  def prime_factors
    Prime.prime_division(num).inject([]) {|pf, p| pf << p[0]}
  end
  
  def construct_string(pf)
    str = ""
    str << "Pling" if pf.include?(3)
    str << "Plang" if pf.include?(5)
    str << "Plong" if pf.include?(7)
    str = num.to_s if str.empty?
    str
  end
end
