class Raindrops
  def self.is_divisible_by?(num, factor)
    num % factor == 0
  end

  def self.convert(num)
    response = ''
    response << 'Pling' if is_divisible_by?(num, 3)
    response << 'Plang' if is_divisible_by?(num, 5)
    response << 'Plong' if is_divisible_by?(num, 7)
    return response unless response.empty?
    num.to_s
  end
end
