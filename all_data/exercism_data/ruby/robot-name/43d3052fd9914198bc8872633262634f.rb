class Robot

  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    'AA'.upto('ZZ').to_a.sample + "%03d" % rand(999)
  end

end
