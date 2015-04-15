class Raindrops

  def self.divisible?(drop, factor)
    if drop % factor == 0 then true end   
  end

  def self.convert(drop)
    result = ''
    factor_messages.each do |factor, message|
      result += message if divisible?(drop, factor)
    end
    result.empty? ? drop.to_s : result
  end

  def self.factor_messages
    { 3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'}
  end
end
