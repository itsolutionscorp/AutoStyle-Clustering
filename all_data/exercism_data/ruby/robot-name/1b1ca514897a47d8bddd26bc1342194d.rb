class Robot
  @names = [] # In real world, names registry should be persisted, say in a DB...

  def initialize
    assign_name
  end

  def name
    @name
  end

  def reset
    @name = nil
    assign_name
  end

  def self.generate_name
    letters = 2.times.map do
      ('A'..'Z').to_a[rand(26)]
    end
    numbers = 3.times.map do
      (0..9).to_a[rand(10)]
    end
    letters.join << numbers.join
  end

  def self.register_name(name)
    @names << name
  end

  def self.already_registered?(name)
    @names.include?(name)
  end

  private
  def assign_name
    while @name.nil?
      name = Robot.generate_name
      # TODO: how to make next two steps atomic?
      # Perhaps register_name() should itself do the
      # check?
      @name = name unless Robot.already_registered?(name)
      Robot.register_name(name)
    end
  end

end