class Robot

  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = random_string + random_number
  end

  private

  def random_string(length=2)
    select_random(length, 'abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ')
 end

  def random_number(length=3)
    select_random(length, '0123456789')
  end

  def select_random(length=10, chars="")
    length.times.collect { chars[rand(chars.size)] }.join
  end
end
