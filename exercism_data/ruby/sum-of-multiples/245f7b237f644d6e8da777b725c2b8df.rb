class SumOfMultiples
  
  attr_accessor :of
  
  def initialize(*multiple_of_list)
    @of=multiple_of_list
  end
  
  def to(n)
    return 0 unless n > 1
    sum = 0
    m = Proc.new { |s| @of.reduce(false) { |a,m| a || s%m==0 } }
    2.upto(n-1) do |s|
      sum += s if m.call(s)
    end  
    sum
  end
  
  def self.to(n)
    SumOfMultiples.new(3,5).to(n)
  end  
  
end  
