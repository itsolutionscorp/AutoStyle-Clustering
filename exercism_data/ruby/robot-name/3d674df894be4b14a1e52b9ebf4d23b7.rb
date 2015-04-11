class Robot
  attr_reader :name

  def initialize
    name_robot
  end

  def reset
    name_robot
  end

  private

  @@alph = [('A'..'Z'),('a'..'z')].map { |i| i.to_a }.flatten
  @@nums = [('0'..'9')].map { |i| i.to_a }.flatten

  def name_robot
    @name = (0..1).map { @@alph[rand(@@alph.length)] }.join
    @name += (0..2).map { @@nums[rand(@@nums.length)] }.join
  end
end
