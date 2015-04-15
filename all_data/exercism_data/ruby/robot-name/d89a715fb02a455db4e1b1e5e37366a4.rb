class Robot

  def name
    @name ||= get_new_name
  end

  def get_new_name
    [*('A'..'Z')].sample(2).join << [*('0'..'9')].sample(3).join
  end

  def reset
    @name = nil
  end

end
