class Robot

  attr_reader :name

  @@robot_names = []

  def initialize
    generate_unique_name
  end

  public

  def reset
    generate_unique_name
  end

  private

  def generate_unique_name
    @name = generate_rand_name
    while @@robot_names.include?(@name)
      @name = generate_rand_name
      puts "colision"
    end
    @@robot_names << @name
  end

  def generate_rand_name
      generate_rand_string(2) << generate_rand_number(4)
  end

  def generate_rand_string(size)
    ('A'..'Z').to_a.sample(size).join
  end

  def generate_rand_number(size)
      (0..9).to_a.sample(size).join
  end

end
