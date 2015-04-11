class Robot
  GENERATOR = Enumerator.new do |y|
    name = 'AA000'
    loop { y << name = name.succ }
  end

  attr_reader :name

  def reset
    @name = GENERATOR.next
  end

  alias :initialize :reset
end
