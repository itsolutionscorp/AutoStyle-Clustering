# == RobotMaster
#
# This class keeps track on all the active and inactive robot names.
# It is used to make sure there are no duplicate names.
# 
# Robot inherits from RobotMaster
#
class RobotMaster
  def self.active_robot_names
    @active_robot_name ||= []
  end

  def self.deactivated_robot_names
    @deactivated_robot_names ||= []
  end

  def name
    raise NotImplementedError
  end

  def is_valid?(string)
    return false if RobotMaster.active_robot_names.include?(string)
    return false if RobotMaster.deactivated_robot_names.include?(string)

    return true
  end

  def enqueue_active_name(string)
    RobotMaster.active_robot_names << string
  end

  def dequeue_active_name
    RobotMaster.active_robot_names.delete(name)
  end

  def enqueue_deactivated_name
    RobotMaster.deactivated_robot_names << name
  end
end

class Robot < RobotMaster
  attr_writer :name
  attr_reader :letters, :numbers

  def initialize
    @letters = ('A'..'Z').to_a
    @numbers = (0..9).to_a
  end

  def self.maximum_letter_count
    2
  end

  def self.maximum_number_count
    3
  end

  def name
    @name ||= generate_name
  end

  def reset
    dequeue_active_name
    enqueue_deactivated_name
    @name = nil
  end

  private

  def generate_name
    new_name = ''

    Robot.maximum_letter_count.times do 
      new_name << letters.fetch(rand(letters.size - 1))
    end

    Robot.maximum_number_count.times do
      new_name << numbers.fetch(rand(numbers.size - 1)).to_s
    end

    if is_valid?(new_name)
      enqueue_active_name new_name
      new_name
    else
      raise "Robot name not uniqe"
    end
  end
end
