class Array
  def keep
    array = []
    each { |n| array << n if (yield n) }
    array
  end
  def discard
    array = []
    each { |n| array << n if !(yield n) }
    array
  end
end
