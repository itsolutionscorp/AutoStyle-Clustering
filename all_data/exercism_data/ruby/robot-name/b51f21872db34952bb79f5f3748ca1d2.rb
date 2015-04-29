class Robot
  def initialize
  end

  def name
    unless @name
      @name = ""
      2.times {@name += "#{("A".."Z").to_a.sample}"}
      3.times {@name += "#{(1..9).to_a.sample}"}
    end
    @name
  end

  def reset
    @name = nil
  end
end
