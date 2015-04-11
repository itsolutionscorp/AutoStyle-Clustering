require 'forwardable'

class Triplet
  class << self
    def where(options = {})
      max_factor = options.fetch(:max_factor, 1)
      min_factor = options.fetch(:min_factor, 1)
      target_sum = options.fetch(:sum, nil)

      permutations = (min_factor..max_factor).to_a.permutation(3).to_a
      triplets = permutations.collect do |factors|
        triplet = Triplet.new(*factors)
        triplet.pythagorean? ? triplet : nil
      end.uniq.compact

      target_sum ?  triplets.select { |t| t.sum == target_sum } : triplets 
    end
  end

  attr_reader :values
  extend Forwardable
  def_delegator :@values, :hash

  def initialize(value1, value2, value3)
    @values = [value1, value2, value3].sort
  end

  def sum
    values.reduce(&:+)
  end

  def product
    values.reduce(&:*)
  end

  def pythagorean?
    (values[0]**2 + values[1]**2) == values[2]**2
  end

  def eql?(comparee)
    product == comparee.product
  end

end
