class Robot
  def initialize
    @name = nil
  end

  def name
    unless @name
      @name = ""
      2.times { @name += ("A".."Z").to_a[rand(26)] }
      3.times { @name += rand(10).to_s }
    end
    @name
  end

  def reset
    @name = nil
  end


end
