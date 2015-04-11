class Array
  def accumulate
    new_array = []
    each do |x|
      new_array << (yield x)
    end
    new_array
  end
end
