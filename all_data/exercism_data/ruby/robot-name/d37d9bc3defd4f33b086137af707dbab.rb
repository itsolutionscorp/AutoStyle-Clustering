class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    random_id + random_number
  end

  def random_id
    2.times.map { (65 + rand(26)).chr }.join
  end

  def random_number
    3.times.map { rand(9) }.join
  end
end
