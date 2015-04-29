require 'set'

class Robot
  attr_reader :name
  @@used_names = Set.new

  def initialize
    reset
  end

  def reset
    @name = generate_name until !@@used_names.include?(@name) && !@name.nil?
    @@used_names << @name
  end

  def generate_name
    ((0..1).map { (65 + rand(26)).chr }.join) + ((0..2).map { rand(10) }.join)
  end

end
