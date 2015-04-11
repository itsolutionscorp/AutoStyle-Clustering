class Binary
  def initialize(num)
    @num = num
  end

  attr_reader :num

  def to_decimal
    return 0 if num =~ /[a-zA-z3-9]/
    arr = num.split('').map!{|x| x.to_i}
    arr.map! do |digit|
      digit * (2 ** ((arr.length - 1) - arr.index(digit)))
    end
    arr.inject(:+)
  end
end
