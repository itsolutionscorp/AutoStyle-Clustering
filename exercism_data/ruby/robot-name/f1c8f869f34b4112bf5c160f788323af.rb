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

  def generate_name
    robo_name = ""
    robo_name << rand(65..90).chr
    robo_name << rand(65..90).chr
    robo_name << rand(100..999).to_s
    robo_name
  end

  def reset
    begin
      robo_name = generate_name
    end while @@robot_names.include? robo_name.to_sym 
    self.name = robo_name
  end
end
