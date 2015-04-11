class Raindrops

  def self.convert number
    new(number).make_it_rain!
  end

  def initialize number
    @number = number
  end

  def make_it_rain!
    return @number.to_s if mutual_drops.empty?
    return build_the_rain mutual_drops
  end

  private

  def mutual_drops
    @mutual_drops ||= factors & raindrops_nums
  end

  def factors
    (2..@number).select do |factor|
      (@number % factor).zero? && is_prime?(factor)
    end
  end

  def build_the_rain(drops)
    cont = ""
    cont += "Pling" if drops.include?(3)
    cont += "Plang" if drops.include?(5)
    cont += "Plong" if drops.include?(7)
    cont
  end

  def raindrops_nums
    [3, 5, 7]
  end

  def is_prime? number
    for num in 2..(number - 1)
      if (number % num) == 0
        return false
      end
    end
    return true
  end

end
