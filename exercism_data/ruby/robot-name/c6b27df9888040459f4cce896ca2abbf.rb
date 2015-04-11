class Robot
  attr_accessor :name
  
  def initialize
    @name = generate_name
  end
  
  def reset
    @name = generate_name
  end
  
  private
  def generate_name
    ("A".."Z").to_a.sample(2).join("") + (100..999).to_a.sample.to_s
  end
  
end
