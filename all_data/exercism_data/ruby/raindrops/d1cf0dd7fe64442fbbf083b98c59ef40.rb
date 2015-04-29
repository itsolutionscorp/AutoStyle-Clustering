class Raindrops
  def self.convert(number)
    conversion = ''
    if number.modulo(3).nonzero? &&
       number.modulo(5).nonzero? &&
       number.modulo(7).nonzero?
      conversion << number.to_s
    end
    if number.modulo(3).zero?
      conversion << 'Pling'
    end
    if number.modulo(5).zero?
      conversion << 'Plang'
    end
    if number.modulo(7).zero?
      conversion << 'Plong'
    end
    conversion
  end
end
