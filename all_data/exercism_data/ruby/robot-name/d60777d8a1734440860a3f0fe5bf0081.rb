class Namer

  @@Names = []

  def initialize

    n = [(1..9)].map { |i| i.to_a }.flatten
    l = [('A'..'Z')].map { |i| i.to_a }.flatten

    @name = ((0...2).map { l[rand(l.length)] } <<
             (0...3).map { n[rand(n.length)] }).join
    if @@Names.include?(@name)
      initialize
    else
      @@Names << @name
    end
  end
end


class Robot

  require 'securerandom'

  def initialize

    @name = Namer.new.to_s

  end

  attr_accessor :name


  def reset
    initialize
  end

end
