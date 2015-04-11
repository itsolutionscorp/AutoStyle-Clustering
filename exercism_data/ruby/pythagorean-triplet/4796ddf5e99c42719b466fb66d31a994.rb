class Triplet
  def self.where *args
    Triplets.where(*args).to_a
  end
  
  attr_reader :a,:b,:c
  def initialize a, b, c
    @a = a
    @b = b
    @c = c
  end
  
  def sum
    @sum ||= [a,b,c].reduce(:+)
  end
  
  def product
    @product ||= [a,b,c].reduce(:*)
  end
  
  def pythagorean?
    @pythagorean ||= a**2 + b**2 == c**2
  end
end

class Triplets
  def self.where max_factor: 3, min_factor: 1, sum: nil
    Triplets.new(max_factor: max_factor, min_factor: min_factor, sum: sum)
  end
  
  attr_reader :sum, :factors
  def initialize max_factor: 3, min_factor: 1, sum: nil
    @sum = sum
    @factors = (min_factor..max_factor).to_a
  end
  
  def to_a
    matching_triplets
  end
  
  private
  def possible_triplets
    @possible_triplets ||= factors.combination(3).map{|vals| Triplet.new *vals }
  end
  
  def pythagorean_triplets
    @pythagorean_triplets ||= possible_triplets.select(&:pythagorean?)
  end
  
  def matching_triplets
    @matching_triplets ||= if sum
      pythagorean_triplets.select{|triplet| triplet.sum == sum }
    else
      pythagorean_triplets
    end
  end
end
