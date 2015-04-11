class Robot
  attr_accessor :name

  def initialize
    @name = ""
    2.times{@name << (65 + rand(25)).chr}

    (0..2).each do |i|
      @name << rand(10).to_s
    end
  end

  def reset
    initialize
  end


end
