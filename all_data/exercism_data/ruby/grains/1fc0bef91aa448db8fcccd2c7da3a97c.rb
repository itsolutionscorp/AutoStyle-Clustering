class Grains
  NUMBER_OF_SQUARES = 64

  def square(number)
    2 ** (number - 1)
  end

  def total
    1.upto(NUMBER_OF_SQUARES).inject { |sum, i| sum + square(i) }
  end
end

def main(stdout = $stdout)
  1.upto(Grains::NUMBER_OF_SQUARES).each do |number|
    stdout.puts "square #{number.to_s.rjust(2)}: #{Grains.new.square(number)}"
  end
  stdout.puts "    total: #{Grains.new.total}"
end

main
