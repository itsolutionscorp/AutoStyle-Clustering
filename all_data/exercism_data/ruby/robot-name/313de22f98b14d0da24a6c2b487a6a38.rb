$robot_names = []

class Robot

  def initialize
    last_name = $robot_names.last
    last_name = "AA000" if last_name == nil
    @numbers = last_name.slice!(2, 3)
    @letters = last_name
    increment!
    $robot_names << self.name
  end

  def name
    @letters + @numbers
  end

  def reset
    initialize
  end

  private

  def increment!
    if @numbers == 999
      @numbers = 0
      self.increment_letters!
    else
      @numbers = @numbers.next
    end
  end

  def increment_letters!
    if @letters[1] == 'Z'
      @letters[1] = 'A'
      @letters[0] = @letters[0].next
    else
      @letters[1] = @letters[1].next
    end
  end
end
