class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    new_name = generate_name
    new_name == name ? reset : @name = new_name
  end

  private
  
  def generate_name
    "#{[(65+rand(26)).chr,(65+rand(26)).chr,rand(9).to_s,rand(9).to_s,rand(9).to_s].join}"
  end
end
