#!/usr/bin/env ruby

# Exercise 7
# Robot Name
# Generate a random name that can be reset

class Robot

  def initialize
    @name = self.reset
  end

  def name
    @name
  end

  def reset

    letters = ''
    numbers = ''

    r = Random.new

    2.times { |x| letters << r.rand(65..90).chr }
    3.times { |x| numbers << r.rand(1..9).to_s }

    @name = (letters + numbers)

  end

end
