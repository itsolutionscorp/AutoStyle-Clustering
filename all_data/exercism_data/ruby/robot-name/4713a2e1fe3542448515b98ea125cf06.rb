class Robot
  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  def generate_name
    "#{rand_letter}#{rand_letter}#{rand(9)}#{rand(9)}#{rand(9)}"
  end

  def rand_letter
    (65 + rand(26)).chr
  end
end
