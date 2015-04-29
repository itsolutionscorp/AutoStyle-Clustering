class Raindrops
  CONVERSIONS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(integer)
    integer = integer.to_i
    str = String.new

    CONVERSIONS.each do |divider, conversion|
      str << conversion if integer % divider == 0
    end

    str = integer.to_s if str.empty?
    str
  end
end
