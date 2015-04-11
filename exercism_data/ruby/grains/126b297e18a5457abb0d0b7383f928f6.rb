class Grains
  def square(space)
    2**(space-1)
  end

  def total
    (1..64).inject{|sum,space| sum + self.square(space)}
  end
end
