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
    @name = (('A'..'Z').to_a.sample(2) + (1..9).to_a.sample(3)).join
  end

end
