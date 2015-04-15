class Robot
  @@names = []
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = (rand(26) + 65).chr + (rand(26) + 65).chr + (rand(9) + 1).to_s + rand(10).to_s + rand(10).to_s
    if @@names.include?(name)
      reset
    else
      @@names << name
    end
  end
end
