class Robot
  attr_accessor :name

  def initialize
    @name = make_name
  end

  def reset
    @name = make_name
  end

  private
  def make_name
    # a name is two Letters (A-Z) followed by 3 digits
    prng = Random.new
    name = ""
    2.times do
      name << prng.rand(65..90)
    end
    3.times do
      name << prng.rand(10).to_s
    end
    name
  end

end
