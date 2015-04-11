class Robot

  def initialize
    @name = ''
  end

  def name
    if @name.empty?
      2.times { @name << rand_string }
      3.times { @name << rand_num }
    end
    @name
  end

  def reset
    @name = ''
  end

  private
  def rand_string
    chars = ('a'..'z').to_a + ('A'..'Z').to_a 
    chars[rand(chars.length)]
  end

  def rand_num
    chars = ('0'..'9').to_a
    chars[rand(chars.length)]
  end

end
