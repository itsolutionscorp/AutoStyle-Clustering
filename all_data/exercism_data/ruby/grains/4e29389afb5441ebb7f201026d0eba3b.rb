# def total
#   total = 0
#   1.upto(64) do |x|
#     total += square(x) 
#   end
#   total
# end
# ^^ Why does this not work?? ^^

class Grains

  def square(num)
    grains = 1
    (num-1).times do
      grains *= 2
    end
    grains
  end

  def total
    total = [] 
    1.upto(64) do |x|
      total << square(x) 
    end
    total.reduce(:+)
  end
end
