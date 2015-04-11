class Robot
  def initialize
    @name = name
  end

  def name
    if @name==nil
    @name = ((0...2).map { (65 + rand(26)).chr }.join).to_s + rand(0..9).to_s + rand(0..9).to_s +
        rand(0..9).to_s
    else
      @name
    end
    @name
  end

  def reset
  @name=nil
  end
end
