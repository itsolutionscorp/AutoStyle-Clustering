class Binary
  def initialize(number)
    @number = number
  end

  def to_decimal
    @number.split.each do |char|
      if char =~ /\D/
        return 0
      end
    end
    power = 0
    solution_array = []
    @number.reverse.split(//).each do |digit|
      solution_array << digit.to_i * 2**power
      power += 1
    end
    sum = 0
    solution_array.each { |a| sum+=a }
    sum
  end
end
