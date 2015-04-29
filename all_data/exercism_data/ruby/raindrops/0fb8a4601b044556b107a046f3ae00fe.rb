class Raindrops

  def self.convert(num)
    "".tap do |factor_string|
      factor_string << 'Pling' if is_factor?(num, 3)
      factor_string << 'Plang' if is_factor?(num, 5)
      factor_string << 'Plong' if is_factor?(num, 7)
      factor_string << "#{num}" if factor_string.empty?
    end
  end

  private

  def self.is_factor?(num, factor)
    num % factor == 0
  end
end
