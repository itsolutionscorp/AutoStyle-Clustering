# Reference: http://math.stackexchange.com/questions/100547/pythagorean-triplets-prime-factors

class Triplet

  def initialize(a,b,c)
    @a,@b,@c = a,b,c
  end

  def self.where(options={})
    triangles = []
    options[:min_factor] ||= 3
    options[:min_factor].upto(options[:max_factor]).each do |a|
      (a+1).upto(options[:max_factor]).each do |b|
        (b+1).upto(options[:max_factor]).each do |c|
          triangle = self.new(a,b,c)
          sum = triangle.sum == options[:sum]
          sum = true if options[:sum].nil?
          triangles << triangle if triangle.pythagorean? && sum
        end
      end
    end
    triangles
  end

  def sum
    @a+@b+@c
  end

  def product
    @a*@b*@c
  end

  def pythagorean?
    @a**2 + @b**2 == @c**2
  end

end
