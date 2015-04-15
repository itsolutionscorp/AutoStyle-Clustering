class Robot
  ALPHABETS = ('A'..'Z').to_a + ('a'..'z').to_a

  attr_reader :name

  @names = {}

  def self.collision?(name)
    if @names[name]
      true
    else
      @names[name] = true
      false
    end
  end

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  def generate_name
    new_name = alphabets + numbers

    self.class.collision?(new_name) ? generate_name : new_name
  end

  private

  def alphabets
    ALPHABETS.shuffle.first(2).join
  end

  def numbers
    rand(0...1000).to_s.rjust(3, '0')
  end
end
