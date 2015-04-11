require 'prime'

class Raindrops

  def self.convert(drop)
    Raindrop.new(drop).make_noise()
  end
end

class Raindrop

  NOISES = {3 => "Pling",
            5 => "Plang",
            7 => "Plong"}

  def initialize(drop)
    @drop = drop
  end

  def make_noise

    noise = ""
    Prime.each(@drop) do |prime|
      noise += NOISES[prime].to_s if @drop % prime == 0
    end
    noise.empty? ? @drop.to_s : noise

  end
end
