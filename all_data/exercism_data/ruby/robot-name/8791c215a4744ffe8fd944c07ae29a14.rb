class Robot
  private
  attr_writer :name
  public
  attr_reader :name
  
  @@robot_names = {}

  def initialize
    self.name = generate_name
    @@robot_names[name.to_sym] = name
  end

  def reset
    begin
      robo_name = generate_name
    end while @@robot_names.include? robo_name.to_sym 
    self.name = robo_name
  end

  private def generate_name
    robo_name = ""
    robo_name << get_random_letter
    robo_name << get_random_letter
    robo_name << get_random_number
    robo_name
  end

  private def get_random_letter
    rand(65..90).chr
  end

  private def get_random_number
    rand(100..999).to_s
  end
end
