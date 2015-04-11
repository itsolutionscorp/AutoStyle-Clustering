class Robot
  attr_accessor :name
  
  def name
    @name || reset
  end
  
  def reset
    @name = RobotName.generate
  end
end

class RobotName
  def self.generate
    recipe = [{:count => 2, :func => generate_char}, {:count => 3, :func => generate_number}]
    recipe.reduce("") do |memo, recip_item|
      memo << recip_item[:count].times.each.map{|k| recip_item[:func]}.join
    end
  end
  
  def self.generate_char
    [*'A'..'Z'].sample
  end  
  
  def self.generate_number
    rand(10).to_s
  end
end
