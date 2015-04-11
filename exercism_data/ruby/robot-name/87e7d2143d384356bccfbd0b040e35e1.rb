class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = new_name
  end

  def new_name
    (letters + numbers).join
  end

  def letters
    ('A'..'Z').to_a.sample(2)
  end

  def numbers
    (0..9).to_a.sample(3)
  end
end
