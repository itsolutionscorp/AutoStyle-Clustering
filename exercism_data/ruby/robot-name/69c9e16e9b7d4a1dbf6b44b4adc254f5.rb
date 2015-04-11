class Robot
  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private
  def generate_name
    'RX83' + random_num
  end

  def random_num
    rand(9).to_s
  end
end
