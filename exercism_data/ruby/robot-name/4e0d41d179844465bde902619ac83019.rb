class Robot

  def name
    @name ||= name_generator
  end

  def reset
    @name = name_generator
  end

  private

  def name_generator
    name = []
    name << [*"A".."Z"].sample(2)
    name << [*1..10].sample(3)
    name.join
  end
end
