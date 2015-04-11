require 'securerandom'
class Robot
  attr_reader :name

  def initialize(name = SecureRandom.hex)
    @name = name
  end

  def reset
    @name = SecureRandom.hex
  end
end
