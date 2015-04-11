require 'set'

module RobotNameFactory
  @used_names = Set.new

  def self.new
    trial_name = make_name

    while @used_names.member?(trial_name) do
      trial_name = make_name
    end

    @used_names << trial_name
    trial_name
  end

  private
  
  def self.make_name
    "#{make_prefix}#{make_suffix}"
  end

  def self.make_prefix
    ('A'..'Z').to_a.shuffle[0..1].join('')
  end

  def self.make_suffix
    rand(899) + 100
  end

end

class Robot
  attr_reader :name
  
  def initialize
    @name = RobotNameFactory.new
  end

  def reset
    initialize
  end

end
