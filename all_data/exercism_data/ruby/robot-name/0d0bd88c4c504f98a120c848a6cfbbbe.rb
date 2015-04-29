class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    initialize
  end

  private

  def generate_name
    name = ''
    2.times do
      name << ('a'..'z').to_a.sample
    end
    name << rand(100).to_s.rjust(3,'0')
  end
end
