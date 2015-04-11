class Robot
  attr_reader :name
  @@used = {}
  def initialize
    reset
  end

  def reset
    begin
      @name = (0..1).map { (65 + rand(26)).chr}.join + rand(1000).to_s.rjust(3,'0')
    end while  @@used[@name]
    @@used[@name] = true
  end

end
