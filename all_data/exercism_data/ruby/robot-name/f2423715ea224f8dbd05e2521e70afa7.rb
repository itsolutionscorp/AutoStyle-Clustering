class Robot
  attr_reader :name

  def initialize
    @name = random_name
  end

  def reset
    @name = random_name
  end

  private
  def random_name
    "#{rand_letter}#{rand_letter}#{rand_number}#{rand_number}#{rand_number}"
  end

  def rand_letter
    (65 + rand(26)).chr
  end

  def rand_number
    rand(10)
  end
end
