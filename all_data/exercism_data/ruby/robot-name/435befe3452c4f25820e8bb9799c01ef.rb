class Robot
  attr_accessor :name

  def initialize()
    @name = generate_name
  end

  def generate_name
    generated_name = ''
    2.times{ generated_name << (65 + rand(25)).chr }
    3.times{ generated_name << rand(10).to_s }

    generated_name
  end

  def reset
    initialize()
  end
end
