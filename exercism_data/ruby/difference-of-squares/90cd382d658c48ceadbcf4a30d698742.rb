#!/usr/bin/env ruby

class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = 0
    square_of_s = 0

    1.upto(@number) do |x|
      sum = sum + x
    end

    square_of_s = (sum)**2

    return square_of_s
  end

  def sum_of_squares
    sum_of_sq = 0
    square = 0

    1.upto(@number) do |x|
      square = (x)**2
      sum_of_sq = sum_of_sq + square
    end

    return sum_of_sq
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end


end
