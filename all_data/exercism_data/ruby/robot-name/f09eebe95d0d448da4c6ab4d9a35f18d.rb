class Robot
  def initialize
    @name = ''
  end

  def reset
    initialize
  end

  def name
    2.times { @name << generate_letters }
    @name << generate_numbers
  end

  def generate_letters
    (rand(26) + 65).to_i.chr
  end

  def generate_numbers
    array = 3.times.map { rand(10) }
    array.join
  end
end
