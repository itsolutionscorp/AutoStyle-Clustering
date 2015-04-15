class Robot

  def initialize()
    @name = (Array.new(2){[*"A".."Z",*"a".."z"].sample} + Array.new(3){[*1..9].sample}).join
  end

  def name
    return @name
  end

  def reset
    initialize
  end

end
