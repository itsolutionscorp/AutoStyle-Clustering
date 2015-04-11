require 'set'

class Robot
  @@set = Set.new []
  def initialize
    unless defined? @z
      initName
    end
  end
  
  def initName
    @z = 32 * rand(2)
    @y = 32 * rand(2)
    @f = (65 + @z + rand(26)).chr
    @l = (65 + @y + rand(26)).chr
    @n = 100 + rand(899)

    if !@@set.add?(name).nil?
      initName
    end
  end
  
  def name
    @f + @l + @n.to_s
  end

  def reset
    @@set.delete(name)
    initName
  end
end
