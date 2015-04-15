require 'active_support'
require 'active_support/core_ext'

class Grains
  @@grains = nil
  @@total_grains = nil

  def initialize
    if @@grains.nil?
      @@grains = generate_grains
    end
    if @@total_grains.nil?
      @@total_grains = @@grains.sum
    end
  end

  def square(square)
    fail ArgumentError, 'Square out of bounds', caller if square < 1 || square > 64
    @@grains[square - 1]
  end

  def total
    @@total_grains
  end

  private

  def generate_grains
    @grain_accumulator = [1]
    (0...63).each do |index|
      @grain_accumulator << @grain_accumulator[index] * 2
    end
    @grain_accumulator
  end
end
