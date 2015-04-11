require 'securerandom'

class Robot
  attr_accessor :name

  def initialize
    set_new_name
  end

  def reset
    set_new_name
  end

  private
  def set_new_name
    @name = generate_name
  end

  def generate_name
    # name must start with two uppercase letters, followed by three numbers
    # I assume collisions are absolutely possible with this method of generating the name
    uuid = SecureRandom.uuid
    letters = uuid.scan(/[a-z]/).first(2).join.upcase
    numbers = uuid.scan(/\d/).first(3).join

    letters.concat(numbers)
  end
end
