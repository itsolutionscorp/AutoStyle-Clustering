class Robot

  def name
    name ||= generate_name
  end

  def reset
    name
  end

  def generate_name
    "AB124" + rand(9).to_s;
  end
  
end
