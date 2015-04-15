require 'set'

class Robot
  attr_reader :name
  @@used_names = Set.new

  def initialize
    reset
  end

  def reset
    @name = generate_name until !@@used_names.include?(@name) && !(@name.to_s == "")
    @@used_names << @name
  end

  def generate_name
    ((0..1).map {('A'..'Z').to_a.sample}.join) + rand(100..999).to_s
  end

end
