class Triplet
  attr_reader :sides

  def self.where(sum: nil, min_factor: 3, max_factor: 10)
    ary = generate_triplets(min_factor, max_factor)
    sum.nil? ? ary : ary.select { |e| e.sum == sum }
  end

  def self.generate_triplets(min, max)
    ary = []
    min.upto(max) do |c|
      min.upto(c) do |b|
        min.upto(b) do |a|
          t = new(a,b,c)
          ary << t if t.pythagorean?
        end
      end
    end
    ary
  end

  def initialize(*sides)
    @sides = sides.sort
  end

  def sum
    sides.reduce(:+)
  end

  def product
    sides.reduce(:*)
  end

  def pythagorean?
    max = sides.last
    sides.first(2).reduce(0) { |sum,s| sum + s**2 } == max**2
  end
end
