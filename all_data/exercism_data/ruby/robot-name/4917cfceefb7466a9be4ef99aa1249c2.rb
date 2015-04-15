class Robot

  @@used = []
  @@counter = 0
  
  def initialize
    @name = ""
    2.times { @name << (65 + rand(26)).chr }
    3.times { @name << rand(10).to_s }
    check_name
  end
  
  def name
    @name
  end

  def reset
    initialize
  end

  private
  def check_name

    if @@counter == 1405
      puts "You are out of robots"
      exit
    end

    if @@used.include?(@name)
      initialize
    end
    
    @@used << @name
    @@counter += 1
  end
  
end
