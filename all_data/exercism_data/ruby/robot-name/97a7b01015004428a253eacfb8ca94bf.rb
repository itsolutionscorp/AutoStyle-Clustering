class Robot
  attr_reader :name

  USED_NAMES = []

  def initialize
    @name = generate_new_name
  end

  def reset
    @name = generate_new_name
  end

  private

  def generate_new_name

    name = ""
    2.times { name << generate_letter }
    3.times { name << generate_number }
    name
  end

  def generate_number
    rand(10).to_s
  end

  def generate_letter
    (65 + rand(25)).chr
  end

end
