class Robot
  attr_reader :name

  @@combinations = ("AA".."ZZ").to_a.collect{|l| (0..999).to_a.collect{|n| l + ("%03d" % n)}}.flatten

  def initialize
    reset
  end

  def reset
    @name = lotto
  end

  private 
  def lotto
    if @@combinations.size < 1
      raise BoundsExceeded, "Exhausted random names using current format", caller
    end

    element = rand(@@combinations.size)
    @@combinations.delete_at(element)
  end

end
