class Raindrops
  def self.convert(number)
    answer=[]
    answer<<divisible_by_3(number)
    answer<<divisible_by_5(number)
    answer<<divisible_by_7(number)
    if answer.compact.empty?
      return number.to_s
    else
      return answer.compact.join
    end
  end

  def self.divisible_by_3(number)
    remainder=number%3
    return 'Pling' if remainder==0
  end

  def self.divisible_by_5(number)
    remainder=number%5
    return 'Plang' if remainder==0
  end

  def self.divisible_by_7(number)
    remainder=number%7
    return 'Plong' if remainder==0
  end
end
