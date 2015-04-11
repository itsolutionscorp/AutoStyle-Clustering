require 'prime'

class Raindrops
  def self.convert(drops, surface: Bucket, counter: RaindropCounts)
    SoundEffect.made_by(drops, surface, counter)
  end
end

class RaindropCounts
  include Enumerable

  def initialize(number)
    @members = Prime.prime_division(number).map(&:first)
  end

  def each &block
    @members.each{|member| block.call(member)}
  end
end

class SoundEffect < SimpleDelegator
  def self.made_by(substance, surface, counter)
    self.new(substance, surface, counter)
  end

  def initialize(substance, surface, counter)
    self.substance = substance
    self.surface = surface
    self.counter = counter
    __setobj__(make_sound)
  end

  private

  attr_accessor :substance, :surface, :counter

  def make_sound
    sound = counter.new(substance).each_with_object("") do |hit, s|
      s << surface.make_sound(hit)
    end
    sound.empty? ? substance.to_s : sound.to_s
  end
end

class Bucket
  def self.make_sound(hit_count)
    case hit_count
    when 3
      "Pling"
    when 5
      "Plang"
    when 7
      "Plong"
    else
      ""
    end
  end
end
