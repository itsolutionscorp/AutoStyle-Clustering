#!/usr/bin/env ruby

class Robot
  attr_reader :name

  def initialize
    @name = Robot.generate_random_name
  end

  def reset
    @name = Robot.generate_random_name
  end

  def self.generate_random_name
    format('%s%s%03d', (65 + rand(26)).chr, (65 + rand(26)).chr, rand(999))
  end
end
