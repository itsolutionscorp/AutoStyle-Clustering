class Robot
public
  a = *("A".."Z")
  n = *(1..26)
  AN = Hash[n.zip(a)]

  def initialize
    @name = makeName
  end

  def name
    @name
  end

  def reset
    @oldName = @name
    while @name == @oldName
      @name = makeName
    end
    @name
  end

private
  def makeName
    rName = ''
    2.times {rName << AN[rand(1..26)]}
    rName += format("%03d", rand(0..999).to_s)
    rName
  end

end
