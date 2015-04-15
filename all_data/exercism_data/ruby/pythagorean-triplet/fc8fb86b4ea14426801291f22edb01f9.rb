class Triplet
  def initialize(a,b,c)
    @a,@b,@c=a,b,c
  end
  
  def sum
    @a+@b+@c
  end
  
  def product
    @a*@b*@c
  end
  
  def pythagorean?
    @a**2+@b**2 == @c**2
  end
  
  def self.where(max_factor: 1,min_factor: 1,sum: nil)
    triplets =[]
    max_factor.downto(min_factor) do |i|
      i.downto(min_factor) do |j|
        j.downto(min_factor) do |k|
          triplet = Triplet.new(j,k,i)
          triplets << triplet if triplet.pythagorean? && (!sum || triplet.sum == sum)
        end
      end
    end  
    triplets
  end
  
end
