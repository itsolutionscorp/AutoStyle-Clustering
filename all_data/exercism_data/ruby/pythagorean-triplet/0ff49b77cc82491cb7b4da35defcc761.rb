class Triplet
  def initialize(*args)
    @triplet = args.sort
  end

  def sum
    @triplet.reduce(:+)
  end

  def product
    @triplet.reduce(:*)
  end

  def pythagorean?
    @triplet[0..1].map { |x| x**2 }.reduce(:+) == @triplet[2] ** 2
  end

  def self.where(min_factor: 1, max_factor:  10, sum: nil)
    result = []
    (min_factor..max_factor).each do |z|
      (min_factor..max_factor).each do |y|
        (y..max_factor).each do |x|
          result << Triplet.new(x, y, z) if x ** 2 + y ** 2 == z ** 2
        end
      end
    end
    if sum
      result.select { |triplet| triplet.sum == sum }
    else
      result
    end
  end

end
