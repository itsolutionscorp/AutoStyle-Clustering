class Couplet
  def initialize(couplet, root, clause)
    @couplet, @root_cause, @clause = couplet, root, clause
  end

  def to_s
    if effect == @root_cause
      "And all for the want of a #{@clause}#{effect}."
    else
      "For want of a #{cause} the #{effect} was lost."
    end
  end

  def cause
    @couplet.first
  end

  def effect
    @couplet.last
  end
end

class Proverb
  def initialize(*causes)
    if causes.last.class == Hash
      @clause = causes.pop[:qualifier] + " "
    end
    @causes = causes
  end

  def to_s
    couplets.map { |couplet| couplet.to_s }.join("\n")
  end

  def couplets
    effects = @causes.clone.rotate
    @causes.zip(effects).map do |pair|
      Couplet.new(pair, @causes.first, @clause)
    end
  end
end
