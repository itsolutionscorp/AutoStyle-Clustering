class Array
  def keep
    result = []
    each { |ele| result << ele if yield(ele) }
    result
  end

  def discard
    result = []
    each { |ele| result << ele unless yield(ele) }
    result
  end
end
