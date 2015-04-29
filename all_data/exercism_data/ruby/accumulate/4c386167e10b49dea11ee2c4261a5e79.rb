class Array
  def accumulate
    new_array = []

    (0...size).each { |i| new_array << (yield at(i)) }

    new_array
  end
end
