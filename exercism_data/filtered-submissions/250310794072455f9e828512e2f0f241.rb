class Hamming

  if self == Hamming
    puts "it's me"
  end

  def compute(x,y)

    differences = 0

    stop = [x.length, y.length].min

    (0...stop).each do |index|

      if x[index] != y[index]
        differences += 1
      end

    end

    differences
  end

end
