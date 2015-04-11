class Fixnum

  def to_roman(input)
    # puts 'input :  ' + @input.to_s
    input.to_i!

    ones = input % 10
    tens = input % 100
    hundreds = input % 1000

    puts 'ones :  ' + @ones.to_s
    puts 'tens :  ' + @tens.to_s
    puts 'hundreds :  ' + @hundreds.to_s
  end

end
