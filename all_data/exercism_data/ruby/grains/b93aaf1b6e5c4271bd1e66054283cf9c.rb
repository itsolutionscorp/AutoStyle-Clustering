class Grains

  def square(number)
    (1..number).inject {|sum| sum * 2}
  end

  def total
    total = []
    (1..64).each do |i|
      total << square(i)
    end
    total.reduce(:+)
   
  end
end
