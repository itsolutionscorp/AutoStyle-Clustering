class Robot

  def initialize
    @name = String.new
  end

  def name
    if @name == String.new
      2.times { @name += ('A'..'Z').to_a.sample }
      3.times { @name += rand(0..9).to_s }
    end
    @name
  end

  def reset
    @name = String.new
  end

end
