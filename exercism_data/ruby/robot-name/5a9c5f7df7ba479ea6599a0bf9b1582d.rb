class Robot
  attr_accessor :name, :prev_names
  
  def initialize
    @name = generate_name
    @prev_names = []
  end
  
  def generate_name
    (0..1).map { ('A'..'Z').to_a[rand(26)] }.join +
      (0..2).map { ('0'..'9').to_a[rand(10)] }.join
  end

  def reset
    @prev_names.push(@name)
    while @prev_names.include?(@name)
      @name = generate_name
    end
  end
end
