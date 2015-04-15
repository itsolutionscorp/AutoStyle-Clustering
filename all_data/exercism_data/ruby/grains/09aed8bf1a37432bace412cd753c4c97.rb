class Grains
  def square(count)
    count.times.inject(0) {|_| _ = _.eql?(0) ? 1 : _ * 2 }
  end

  def total
    square(65) - 1
  end
end
