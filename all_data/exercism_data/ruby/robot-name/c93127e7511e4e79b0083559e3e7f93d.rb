# Robot Name Exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

require 'singleton'

# use singleton RobotName
class RobotName
  include Singleton
  attr_accessor :names

  def initialize
    @names = []
  end

  def <<(object)
    @names.append(object)
  end
end

class Robot
  attr_accessor :name

  def initialize
    @name ||= generate_name
  end

  def name
    @name.nil? ? (@name = generate_name) : @name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    generate = lambda {((0..1).map { (65 + rand(26)).chr } + (0..2).map{rand(10)}).join}
    while RobotName.instance.names.include?(@name) || @name.nil?
      @name = generate.call
    end
    RobotName.instance.names << name
    @name
  end
end
