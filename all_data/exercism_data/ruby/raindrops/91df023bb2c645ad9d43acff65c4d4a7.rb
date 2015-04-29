module Raindrops
  NAMED_FACTORS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    names = []
    NAMED_FACTORS.each do |factor, name|
      if number % factor == 0
        names << name
      end
    end

    if names.empty?
      number.to_s
    else
      names.join('')
    end
  end
end
