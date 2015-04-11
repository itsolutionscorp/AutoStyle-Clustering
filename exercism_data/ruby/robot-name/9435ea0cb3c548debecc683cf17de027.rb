class Robot
  def initialize
    @@names = []
  end

  def name
    return @name if @name

    temp = ((1..2).map { rand(36).to_s(36).upcase } + (1..3).map{ rand(10) }).join

    return name if @@names.include? temp # Try again!

    @@names << temp
    @name = temp
  end

  def non_unique_name
    @name ||= ((1..2).map { rand(36).to_s(36).upcase } + (1..3).map{ rand(10) }).join
  end

  def reset
    @name = nil
  end
end
