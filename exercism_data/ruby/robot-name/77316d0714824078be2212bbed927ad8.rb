class Robot

  def name
    @name ||= name_generator
  end

  def reset
    @name = name_generator
  end

  private

  def name_generator
    name = [*"A".."Z"].sample(2).join + [*1..10].sample(3).join
  end
end
