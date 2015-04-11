class Robot

  def name
    @name ||= create_name
  end

  def reset
    @name = create_name
  end

  def create_name
    @name = ""
    2.times { @name.concat(rand(65..90)) }
    3.times { @name.concat(rand(10).to_s) }
    @name
  end
end
