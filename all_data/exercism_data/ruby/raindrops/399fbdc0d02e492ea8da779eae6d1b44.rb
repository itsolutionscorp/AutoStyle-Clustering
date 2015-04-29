module Raindrops
  CONVERT_RULES = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(drop)
    converted = ''
    CONVERT_RULES.each do |rule, value|
      converted << value if drop % rule == 0
    end

    converted == '' ? drop.to_s : converted
  end
end
