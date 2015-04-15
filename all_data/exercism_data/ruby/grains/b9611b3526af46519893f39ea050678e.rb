class Grains
  NUMBER_OF_SQUARES = 64

  def square(number)
    2 ** (number - 1)
  end

  def total
    square(NUMBER_OF_SQUARES + 1) - 1
  end
end

if __FILE__ == $0
  1.upto(Grains::NUMBER_OF_SQUARES).each do |number|
    puts "square #{number.to_s.rjust(2)}: #{Grains.new.square(number)}"
  end
  puts "    total: #{Grains.new.total}"
end
