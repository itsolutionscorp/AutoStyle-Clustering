require 'set'

class Robot
  attr_accessor :name, :robot_names
  @@robot_names = Set.new

  def name
    @name ||= "#{alpha}#{numeric}"
    if validate_name
      @name
    end
  end

  def validate_name
    if @@robot_names.include? @name
      return false
    else
      @@robot_names << @name
    end
  end

  def call_me
    @@robot_names
  end

  def alphabet
    ('a'..'z').to_a + ('A'..'Z').to_a
  end

  def alpha
    alphabet.sample(2).join('')
  end

  def numeric
    rand(100..999)
  end

  def reset
    @name = nil
  end
end
