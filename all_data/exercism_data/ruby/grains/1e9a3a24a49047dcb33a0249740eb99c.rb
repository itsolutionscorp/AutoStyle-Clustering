class Grains
  def square(i)
    2 ** (i-1)
  end

  def total
    output = 0

    64.times do |x|
      output += square(x+1)
    end

    output
  end
end
