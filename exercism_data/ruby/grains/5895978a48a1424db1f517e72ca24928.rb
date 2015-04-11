class Grains
  def square(number)
    sum_of_grains = 1
    
    (number - 1).times do
      sum_of_grains *= 2
    end

    sum_of_grains
  end

  def total
    total_grains = 0

    64.times do |n|
      total_grains += square(n + 1)
    end

    total_grains
  end
end
