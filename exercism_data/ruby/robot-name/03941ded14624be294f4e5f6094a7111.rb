class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    name = []
    name << [*"a".."z"].sample(2)
    name << [*0..9].sample(3)
    name.join
  end

end
