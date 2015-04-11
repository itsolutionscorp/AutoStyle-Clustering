class Robot
  attr_accessor :name

  def initialize
    @name = set_name!
  end

  def reset
    set_name!
  end


  private

  def set_name!
    @name = random_letters(2)
    @name << (0...3).map { rand(9)}.join
  end

  def random_letters(count)
    (0...count).map { (65 + rand(26)).chr }.join
  end
end
