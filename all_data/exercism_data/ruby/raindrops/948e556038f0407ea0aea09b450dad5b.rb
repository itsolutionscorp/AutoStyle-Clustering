class Fixnum
  def divisible_by?(number)
    (self % number).zero?
  end
end

class Raindrops
  @types_of_drops = {
    3 => :Pling,
    5 => :Plang,
    7 => :Plong
  }

  def self.convert(number)
    raindrops = ""
    @types_of_drops.each { |drop, sound| raindrops << sound.to_s if number.divisible_by?(drop) }

    if raindrops.empty?
      number.to_s
    else
      raindrops
    end
  end
end