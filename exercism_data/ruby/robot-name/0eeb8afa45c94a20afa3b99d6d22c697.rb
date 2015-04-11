class Robot
  private
  attr_writer :name
  public
  attr_reader :name
  
  @@robot_names = {}

  def initialize
    begin
      self.name = generate_name
    end while @@robot_names.include? name.to_sym 
    @@robot_names[name.to_sym] = name
  end

  def reset
    begin
      robo_name = generate_name
    end while @@robot_names.include? robo_name.to_sym 
    self.name = robo_name
  end

  def generate_name
    robo_name = ""
    robo_name << get_random_letter
    robo_name << get_random_letter
    robo_name << get_random_number
    robo_name
  end

  def get_random_letter
    rand(65..90).chr
  end

  def get_random_number
    rand(100..999).to_s
  end

  private :generate_name, :get_random_letter, :get_random_number
end
