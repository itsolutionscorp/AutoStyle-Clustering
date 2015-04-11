class Raindrops

  def self.convert(n)
    result = ''
    factor_messages.each do |factor, message|
      result += message if n.divisible?(factor)
    end
    result.empty? ? n.to_s : result
  end

  private

  def self.factor_messages
    { 3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong' }
  end

end

class Integer
  def divisible?(n)
    self % n == 0
  end
end
