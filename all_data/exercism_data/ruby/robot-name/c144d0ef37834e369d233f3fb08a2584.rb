require 'securerandom'
class Robot
  attr_accessor :name
  def initialize
    @name = create_name
  end

  def create_name
    "RB#{SecureRandom.random_number(10)}#{SecureRandom.random_number(10)}#{SecureRandom.random_number(10)}"
  end

  def reset
    @name = create_name
  end
end
