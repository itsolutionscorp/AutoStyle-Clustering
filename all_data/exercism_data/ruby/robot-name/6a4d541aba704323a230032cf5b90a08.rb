class Robot
  @@last_name = ""

  def initialize
    @name = ""
    generate_name
  end

  def generate_name
    @@last_name = @name = "AA000" if(@@last_name.empty?)
    @name = increase_name(@@last_name) if(@name.empty?)

    @@last_name = @name
  end

  def increase_name(name)
    name.next()
  end

  def reset
    @name = ""
    generate_name
  end

  attr_reader :name

  private :generate_name
end
