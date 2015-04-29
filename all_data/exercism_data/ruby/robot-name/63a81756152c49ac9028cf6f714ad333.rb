class Robot
  @names = []
  class << self; attr_reader :names end

  def name
    @name ||= generate_unique_name
  end

  def reset
    @name = nil
  end

  private

  def generate_unique_name
    name = ''
    loop { break unless Robot.names.include?(name = generate_name) }
    Robot.names << name
    name
  end

  def generate_name
    name = ''
    2.times { name << rand_letter }
    3.times { name << rand_digit }
    name
  end

  def rand_letter
    rand_from_range(('A'..'Z'))
  end

  def rand_digit
    rand_from_range(('0'..'9'))
  end

  def rand_from_range(range)
    range.to_a[rand(range.count)]
  end
end
