require 'securerandom'

class Robot

attr_accessor :name

  def name
    if !defined? @name
      @name = (0..1).map{65.+(rand(26)).chr}.join
      @name += Random.new.rand(100..999).to_s      
    else
      return @name
    end
  end
  
  def reset
    remove_instance_variable(:@name)
  end
end
