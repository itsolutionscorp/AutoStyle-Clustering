#!/usr/bin/ruby robot.rb

class Robot
  require 'time'
  require 'date'
  attr_accessor :name

  def initialize
    @name = serialize
    @name = @name.call
  end

  def serialize
    lambda{
      t = Time.now.to_i
      serial_number = ((rand t) % 1000).to_s
      while serial_number.length < 3
        serial_number.prepend("0")
      end
      lA = ((t % 26) + 65).chr
      lB = (((t / 2) % 26) + 65).chr
      [lA, lB, serial_number].join.to_s
    }
  end

  def reset
    Robot.new
  end

end
