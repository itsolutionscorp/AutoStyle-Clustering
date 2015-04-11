require 'pry'

class Triplet
  attr_reader :first, :second, :third

  def initialize(*args)
    @first, @second, @third = args[0], args[1], args[2]
  end

  def sum
    angles.inject(&:+)
  end

  def product
    angles.inject(&:*)
  end

  def pythagorean?
    angles[0]**2 + angles[1]**2 == angles[2]**2
  end

  def angles
    [first, second, third].sort
  end

  def self.where(options)
    min = options[:min_factor] || 1
    max = options[:max_factor]
    sum = options[:sum]
    result = loop_from_hell(min, max)
    sum ? match_sum(sum, result) : result
  end

  private

  def self.loop_from_hell(min, max)
    triplets = []
    (min..max).to_a.each do |x|
      (min..max).to_a.each do |y|
        (min..max).to_a.each do |z|
          t = Triplet.new(x, y, z)
          if t.pythagorean? && !in_collection?(triplets, t)
            triplets << t
          end
        end
      end
    end
    triplets
  end

  def self.in_collection?(collection, triplet)
    collection.any? {|x| x.angles == triplet.angles}
  end

  def self.match_sum(sum, collection)
    collection.select {|t| t.sum == sum}
  end

end
