class Triplet
  def self.where(conditions={})
    set_conditions(conditions)
    pythagorean_triplets
  end

  class << self; attr_reader :max, :min, :target_sum; end
  def self.set_conditions(conditions)
    @max = conditions.fetch(:max_factor, 1000)
    @min = conditions.fetch(:min_factor, 1)
    @target_sum = conditions.fetch(:sum, nil)
  end

  def self.pythagorean_triplets
    Triplets.new(max, min).reduce([]) do |pt,(a,b,c)|
      triplet = Triplet.new(a,b,c)
      triplet.valid? ? pt<<triplet : pt
    end
  end

  attr_reader :a, :b, :c, :target_sum
  def initialize(a,b,c)
    @a,@b,@c = a,b,c
    @target_sum = Triplet.target_sum
  end

  def triplet
    [a,b,c]
  end

  def sum
    a+b+c
  end

  def product
    a*b*c
  end

  def pythagorean?
    a**2 + b**2 == c**2
  end

  def sum_target_met?
    target_sum ? (sum == target_sum) : true
  end

  def valid?
    sum_target_met? && pythagorean?
  end
end


class Triplets
  include Enumerable
  attr_reader :max, :min
  def initialize(max_factor, min_factor)
    @max, @min = max_factor, min_factor
  end

  def each
    (min..max).each {|a| (a..max).each {|b| (min..max).each {|c| yield a,b,c }}}
  end
end


require 'minitest/autorun'
class TripletTest < MiniTest::Test
  def test_triplets_where_sum_1000
    #skip
    triplets = Triplet.where(sum: 1000, max_factor: 500)
    assert_equal 1, triplets.size
    assert_equal [200,375,425], triplets.first.triplet
  end
end
