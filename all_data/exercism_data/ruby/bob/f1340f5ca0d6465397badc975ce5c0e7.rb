class Bob
  def hey(remark)
    remark = Remark.new(remark)

    if remark.silent?
      "Fine. Be that way!"
    elsif remark.shouting?
      "Woah, chill out!"
    elsif remark.telling?
      "Whatever." 
    elsif remark.asking?
      "Sure."
    end
  end
end

class Remark
  def initialize(remark)
    @remark = remark.to_s.strip
  end

  def silent?
    @remark == ''
  end

  def shouting?
    @remark == @remark.upcase
  end

  def telling?
    @remark.end_with?('.') || @remark.end_with?('!')
  end

  def asking?
    @remark.end_with?('?')
  end
end
